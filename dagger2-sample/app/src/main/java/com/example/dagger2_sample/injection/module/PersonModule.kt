package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module
class PersonModule {

    @Provides
    fun provideName(): String = "leveloper"

    @Provides
    fun provideAge(): Int = 28
}