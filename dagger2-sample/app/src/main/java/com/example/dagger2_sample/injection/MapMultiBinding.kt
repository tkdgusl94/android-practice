package com.example.dagger2_sample.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Component(
    modules = [
        MapModule::class
    ]
)
interface MapComponent {
    fun getMap(): Map<String, Int>
}

@Module
class MapModule {

    @Provides
    @IntoMap
    @StringKey("key1")
    fun provideKey1() = 100

    @Provides
    @IntoMap
    @StringKey("key2")
    fun provideKey2() = 200
}