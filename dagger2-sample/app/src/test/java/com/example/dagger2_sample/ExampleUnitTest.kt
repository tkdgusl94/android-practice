package com.example.dagger2_sample

import com.example.dagger2_sample.injection.DaggerCounterComponent
import com.example.dagger2_sample.injection.DaggerMyComponent1
import com.example.dagger2_sample.injection.DaggerMyComponent2
import com.example.dagger2_sample.injection.DaggerMyComponent3
import com.example.dagger2_sample.injection.DaggerMyComponent4
import com.example.dagger2_sample.injection.DaggerPersonComponent
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test_hello_world() {
        val component = DaggerMyComponent1.create()
        assert(component.getHelloWorld() == "hello world")
    }

    @Test
    fun test_person() {
        val component = DaggerMyComponent2.create()
        assert(component.getPerson() == Person("leveloper", 28))
    }

    @Test
    fun test_nullable() {
        val component = DaggerMyComponent3.create()
        assert(component.getInteger() == null)
    }

    @Test
    fun test_sub_module() {
        val component = DaggerMyComponent4.create()
        assert(component.getName() == "leveloper 28")
    }

    @Test
    fun test_person_2() {
        val component = DaggerPersonComponent.create()
        assert(component.getPersonA() == PersonA("leveloper", 28))

        val personB = PersonB()
        component.inject(personB)
        assert(personB.name == "leveloper")
    }

    @Test
    fun test_lazy() {
        val component = DaggerCounterComponent.create()
        val counter = Counter()
        component.inject(counter)
        counter.printLazy()
    }

    @Test
    fun test_provider() {
        val component = DaggerCounterComponent.create()
        val counter = Counter()
        component.inject(counter)
        counter.printProvider()
    }
}