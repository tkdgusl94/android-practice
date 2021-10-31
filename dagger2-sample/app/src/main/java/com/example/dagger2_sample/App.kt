package com.example.dagger2_sample

import android.app.Application
import com.example.dagger2_sample.injection.android.ApplicationComponent
import com.example.dagger2_sample.injection.android.DaggerApplicationComponent

class App : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}