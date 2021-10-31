package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module
class MyModule1 {

    @Provides
    fun provideHelloWorld(): String = "hello world"
}

