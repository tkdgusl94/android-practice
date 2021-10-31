package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SingletonModule::class
    ]
)
interface SingletonComponent {
    fun getObject(): Any
}

@Module
class SingletonModule {

    @Singleton
    @Provides
    fun provideObject() = Any()
}