package com.example.dagger2_sample.injection

import com.example.dagger2_sample.injection.module.MyModule3
import dagger.Component

@Component(
    modules = [
        MyModule3::class
    ])
interface MyComponent3 {
    fun getInteger(): Int?
}