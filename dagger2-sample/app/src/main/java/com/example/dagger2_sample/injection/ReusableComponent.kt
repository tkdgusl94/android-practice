package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ReusableModule::class
    ]
)
interface ReusableComponent {
    fun getObject(): Any
}

@Module
class ReusableModule {

    @Reusable
    @Provides
    fun provideObject() = Any()
}