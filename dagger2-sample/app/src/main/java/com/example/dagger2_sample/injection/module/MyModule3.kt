package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module
class MyModule3 {

    @Provides
    fun provideInteger(): Int? = null
}

