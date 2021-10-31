package com.example.dagger2_sample.data

import javax.inject.Inject

data class PersonA @Inject constructor(
    val name: String,
    val age: Int
)