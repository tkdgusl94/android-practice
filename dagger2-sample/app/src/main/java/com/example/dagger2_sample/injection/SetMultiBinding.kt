package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Component(
    modules = [
        SetModule::class
    ]
)
interface SetComponent {
    fun getSet(): Set<String>
}

@Module
class SetModule {

    @Provides
    @IntoSet
    fun provideHello(): String = "hello"

    @Provides
    @IntoSet
    fun provideWorld(): String = "world"
}