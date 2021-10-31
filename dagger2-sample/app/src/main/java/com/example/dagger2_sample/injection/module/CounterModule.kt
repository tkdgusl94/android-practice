package com.example.dagger2_sample.injection.module

import dagger.Module
import dagger.Provides

@Module
class CounterModule {

    private var next = 100

    @Provides
    fun provideInteger(): Int {
        println("computing...")
        return next++
    }
}