package com.example.dagger2_sample.injection

import com.example.dagger2_sample.data.Person
import com.example.dagger2_sample.injection.module.MyModule2
import dagger.Component

@Component(
    modules = [
        MyModule2::class
    ])
interface MyComponent2 {
    fun getPerson(): Person
}