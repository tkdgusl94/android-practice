package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoSet

@Component(
    modules = [
        ParentModule::class
    ]
)
interface ParentComponent {
    fun getStrings(): Set<String>
    fun getChildComponentBuilder(): ChildComponent.Builder
}

@Module(
    subcomponents = [
        ChildComponent::class
    ]
)
class ParentModule {

    @Provides
    @IntoSet
    fun provideString1() = "parent string 1"

    @Provides
    @IntoSet
    fun provideString2() = "parent string 2"
}

@Subcomponent(
    modules = [
        ChildModule::class
    ]
)
interface ChildComponent {
    fun getStrings(): Set<String>

    @Subcomponent.Builder
    interface Builder {
        fun build(): ChildComponent
    }
}

@Module
class ChildModule {

    @Provides
    @IntoSet
    fun provideString1() = "child string 1"

    @Provides
    @IntoSet
    fun provideString2() = "child string 2"
}