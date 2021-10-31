package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module
class DuplicationModule {

    @Provides
    fun provideHelloWorld(): String = "hello world"

    @Provides
    fun provideName(): String = "leveloper"
}