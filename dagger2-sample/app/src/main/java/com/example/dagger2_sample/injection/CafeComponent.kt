package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject

class CoffeeBean

class Water

data class Coffee @Inject constructor(
    val coffeeBean: CoffeeBean,
    val water: Water,
)

class Machine @Inject constructor(
    builder: MachineComponent.Builder
) {

    private val component = builder.withMachineModule(MachineModule()).build()

    fun extract(): Coffee {
        return component.getCoffee()
    }
}

class Cafe @Inject constructor(
    private val coffeeMachine: Machine,
) {
    fun orderCoffee(): Coffee {
        return coffeeMachine.extract()
    }
}

/**
 * MachineComponent
 */
@Subcomponent(
    modules = [
        MachineModule::class
    ]
)
interface MachineComponent {
    fun getCoffee(): Coffee

    @Subcomponent.Builder
    interface Builder {
        fun withMachineModule(coffeeMachineModule: MachineModule): Builder
        fun build(): MachineComponent
    }
}

@Module
class MachineModule {

    @Provides
    fun provideCoffeeBean() = CoffeeBean()
}

@Component(
    modules = [
        CafeModule::class
    ]
)
interface CafeComponent {
    fun getCafe(): Cafe
}

@Module(
    subcomponents = [
        MachineComponent::class
    ]
)
class CafeModule {

    @Provides
    fun provideCafe(machine: Machine) = Cafe(machine)

    @Provides
    fun provideWater() = Water()

//    @Provides
//    fun provideMachine(builder: MachineComponent.Builder): Machine {
//        return Machine(builder)
//    }
}