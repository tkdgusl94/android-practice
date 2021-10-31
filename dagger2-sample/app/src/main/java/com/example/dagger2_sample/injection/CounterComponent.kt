package com.example.dagger2_sample.injection

import com.example.dagger2_sample.Counter
import com.example.dagger2_sample.injection.module.CounterModule
import dagger.Component

@Component(
    modules = [
        CounterModule::class
    ]
)
interface CounterComponent {

    fun inject(counter: Counter)
}