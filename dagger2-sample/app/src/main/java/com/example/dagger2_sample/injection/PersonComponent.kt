package com.example.dagger2_sample.injection

import com.example.dagger2_sample.data.PersonA
import com.example.dagger2_sample.data.PersonB
import com.example.dagger2_sample.injection.module.PersonModule
import dagger.Component

@Component(
    modules = [
        PersonModule::class
    ]
)
interface PersonComponent {

    fun getPersonA(): PersonA

    fun inject(personB: PersonB)
}