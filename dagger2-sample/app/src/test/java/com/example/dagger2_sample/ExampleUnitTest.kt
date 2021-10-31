package com.example.dagger2_sample

import com.example.dagger2_sample.injection.Animal
import com.example.dagger2_sample.injection.Cafe
import com.example.dagger2_sample.injection.DaggerCafeComponent
import com.example.dagger2_sample.injection.DaggerCounterComponent
import com.example.dagger2_sample.injection.DaggerCustomMapComponent
import com.example.dagger2_sample.injection.DaggerMapComponent
import com.example.dagger2_sample.injection.DaggerMultiBindsComponent
import com.example.dagger2_sample.injection.DaggerMyComponent1
import com.example.dagger2_sample.injection.DaggerMyComponent2
import com.example.dagger2_sample.injection.DaggerMyComponent3
import com.example.dagger2_sample.injection.DaggerMyComponent4
import com.example.dagger2_sample.injection.DaggerParentComponent
import com.example.dagger2_sample.injection.DaggerPersonComponent
import com.example.dagger2_sample.injection.DaggerReusableComponent
import com.example.dagger2_sample.injection.DaggerSetComponent
import com.example.dagger2_sample.injection.DaggerSingletonComponent
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

    @Test
    fun test_singleton() {
        val component = DaggerSingletonComponent.create()
        val object1 = component.getObject()
        val object2 = component.getObject()
        assert(object1 == object2)
    }

    @Test
    fun test_reusable() {
        val component = DaggerReusableComponent.create()
        val object1 = component.getObject()
        val object2 = component.getObject()
        assert(object1 == object2)
    }

    @Test
    fun test_set_multi_binding() {
        val component = DaggerSetComponent.create()
        val set = component.getSet()

        assert(set.size == 2)
        assert(set.contains("hello"))
        assert(set.contains("world"))
    }

    @Test
    fun test_map_multi_binding() {
        val component = DaggerMapComponent.create()
        val map = component.getMap()

        assert(map.size == 2)
        assert(map["key1"] == 100)
        assert(map["key2"] == 200)
    }

    @Test
    fun test_custom_map_multi_binding() {
        val component = DaggerCustomMapComponent.create()
        val map = component.getStringsByAnimal()

        assert(map.size == 2)
        assert(map[Animal.CAT] == "Meow")
        assert(map[Animal.DOG] == "Bow-wow")
    }

    @Test
    fun test_subcomponent_multi_binding() {
        val parentComponent = DaggerParentComponent.create()
        val childComponent = parentComponent.getChildComponentBuilder().build()

        val parentSet = parentComponent.getStrings()
        val childSet = childComponent.getStrings()

        assert(parentSet.size == 2)
        assert(childSet.size == 4)
    }

    @Test
    fun test_abstract_multi_binding() {
        val component = DaggerMultiBindsComponent.create()
        val stringSet = component.getStrings()

        assert(stringSet.isEmpty())
    }

    @Test
    fun test_cafe_coffee() {
        val component = DaggerCafeComponent.create()
        val cafe = component.getCafe()

        println(cafe.orderCoffee())
        println(cafe.orderCoffee())
        println(cafe.orderCoffee())

        assert(false)
    }
}