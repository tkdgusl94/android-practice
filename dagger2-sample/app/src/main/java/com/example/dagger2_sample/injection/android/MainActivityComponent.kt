package com.example.dagger2_sample.injection.android

import com.example.dagger2_sample.MainActivity
import com.example.dagger2_sample.data.Person
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

@ActivityScope
@Subcomponent(modules = [
    MainActivityModule::class
])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}

@Module
class MainActivityModule {

    @Named("activity")
    @ActivityScope
    @Provides
    fun providePerson(): Person = Person("leveloper", 28)
}