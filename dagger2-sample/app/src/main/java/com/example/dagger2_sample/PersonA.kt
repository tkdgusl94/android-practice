package com.example.dagger2_sample

import javax.inject.Inject

data class PersonA @Inject constructor(
    val name: String,
    val age: Int
)