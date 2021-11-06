package com.example.dagger2_sample.injection.android

import android.content.Context
import android.content.SharedPreferences
import com.example.dagger2_sample.App
import com.example.dagger2_sample.MainActivity
import com.example.dagger2_sample.data.Person
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class
])
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}

@Module
abstract class ApplicationModule {

    @Named("application")
    @Provides
    fun providePerson(): Person = Person("application", 28)

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}