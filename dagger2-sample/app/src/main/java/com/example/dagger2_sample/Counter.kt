package com.example.dagger2_sample

import dagger.Lazy
import javax.inject.Inject
import javax.inject.Provider

class Counter {

    @Inject
    lateinit var lazy: Lazy<Int>

    @Inject
    lateinit var provider: Provider<Int>

    fun printLazy() {
        println("printing...")
        println(lazy.get())
        println(lazy.get())
        println(lazy.get())
    }

    fun printProvider() {
        println("printing...")
        println(provider.get())
        println(provider.get())
        println(provider.get())
    }
}