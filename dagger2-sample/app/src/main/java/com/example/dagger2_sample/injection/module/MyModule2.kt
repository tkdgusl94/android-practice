package com.example.dagger2_sample.injection.module

import com.example.dagger2_sample.Person
import dagger.Module
import dagger.Provides

@Module
class MyModule2 {

    @Provides
    fun provideName(): String = "leveloper"

    @Provides
    fun provideAge(): Int = 28

    @Provides
    fun providePerson(name: String, age: Int): Person = Person(name, age)
}