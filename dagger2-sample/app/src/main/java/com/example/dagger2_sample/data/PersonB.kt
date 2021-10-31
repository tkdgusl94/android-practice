package com.example.dagger2_sample.data

import javax.inject.Inject

class PersonB {

    @Inject
    lateinit var name: String

    override fun toString(): String {
        return "name: $name"
    }
}