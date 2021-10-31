package com.example.dagger2_sample.injection.android

import android.content.Context
import android.content.SharedPreferences
import com.example.dagger2_sample.App
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun mainActivityComponentBuilder(): MainActivityComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): ApplicationComponent
    }
}

@Module(subcomponents = [MainActivityComponent::class])
class ApplicationModule {

    @Singleton
    @Provides
    fun provideSharedPreference(app: App): SharedPreferences {
        println("provide sharedPreference")
        return app.getSharedPreferences("default", Context.MODE_PRIVATE)
    }
}