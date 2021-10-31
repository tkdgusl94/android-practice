package com.example.dagger2_sample.injection.android

import com.example.dagger2_sample.MainActivity
import com.example.dagger2_sample.data.Person
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(activity: MainActivity): Builder
        fun setModule(module: MainActivityModule): Builder
        fun build(): MainActivityComponent
    }
}

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    fun providePerson(): Person = Person("leveloper", 28)
}