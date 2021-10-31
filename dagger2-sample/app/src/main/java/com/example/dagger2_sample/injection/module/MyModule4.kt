package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module(includes = [SubModule::class])
class MyModule4 {

    @Provides
    fun provideName(age: Int): String = "leveloper $age"
}

@Module
class SubModule {

    @Provides
    fun provideAge(): Int = 28
}