package com.example.dagger2_sample.injection

import com.example.dagger2_sample.injection.module.MyModule4
import dagger.Component

@Component(
    modules = [
        MyModule4::class
    ])
interface MyComponent4 {
    fun getName(): String
}