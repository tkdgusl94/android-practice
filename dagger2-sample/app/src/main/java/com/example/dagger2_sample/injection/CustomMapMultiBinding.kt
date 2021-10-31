package com.example.dagger2_sample.injection

import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

enum class Animal {
    CAT, DOG
}

@MapKey
annotation class AnimalKey(val value: Animal)

@Component(
    modules = [
        CustomMapModule::class
    ]
)
interface CustomMapComponent {
    fun getStringsByAnimal(): Map<Animal, String>
}

@Module
class CustomMapModule {

    @Provides
    @IntoMap
    @AnimalKey(Animal.CAT)
    fun provideCat(): String = "Meow"

    @Provides
    @IntoMap
    @AnimalKey(Animal.DOG)
    fun provideDog(): String = "Bow-wow"
}