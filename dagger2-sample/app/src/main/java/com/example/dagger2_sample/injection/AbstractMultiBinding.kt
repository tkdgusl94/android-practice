package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class MultiBindsModule {
    @Multibinds
    abstract fun getStrings(): Set<String>
}

@Component(
    modules = [
        MultiBindsModule::class
    ]
)
interface MultiBindsComponent {
    fun getStrings(): Set<String>
}