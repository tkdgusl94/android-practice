package com.example.dagger2_sample

import javax.inject.Inject

class PersonB {

    @Inject
    lateinit var name: String

    override fun toString(): String {
        return "name: $name"
    }
}