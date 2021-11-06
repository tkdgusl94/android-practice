package com.example.dagger2_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger2_sample.data.Person
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Named("application")
    @Inject
    lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}