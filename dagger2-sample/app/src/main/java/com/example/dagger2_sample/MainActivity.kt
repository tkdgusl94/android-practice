package com.example.dagger2_sample

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger2_sample.data.Person
import com.example.dagger2_sample.injection.android.MainActivityComponent
import com.example.dagger2_sample.injection.android.MainActivityModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var person: Person

    val component: MainActivityComponent by lazy {
        (application as App).applicationComponent
            .mainActivityComponentBuilder()
            .setModule(MainActivityModule())
            .setActivity(this)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        println("shared: $sharedPreferences")
        println("person: $person")
    }
}