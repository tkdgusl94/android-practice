package com.example.rx_sample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Test

import org.junit.Assert.*
import org.reactivestreams.Publisher

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private fun divider() = println("------------------------------------")

    private fun <T> Observable<T>.print() = subscribe(
        System.out::println,
        System.out::println
    ) { println("onComplete") }

    private fun <T> Single<T>.print() = subscribe(
        System.out::println,
        System.out::println
    )

    @Test
    fun test01() {
        val items = PublishSubject.create<Int>()

        items.onNext(1)
        items.onNext(2)
        items.onNext(3)
        items.onNext(4)

        items.filter { it % 2 == 0 }
            .print()

        items.onNext(5)
        items.onNext(6)
        items.onNext(7)
        items.onNext(8)
    }

    /**
     * Observable.create()를 사용하면 Emitter를 이용하여 직접 아이템을 발행한다.
     * onComplete()는 아이템 발행의 완료를 뜻한다. 완료가 되면 그 이후에 발행된 아이템은 무시된다.
     */
    @Test
    fun test02_create() {
        val source1 = Observable.create<String> {
            it.onNext("hello")
            it.onNext("world")
            it.onComplete()
        }

        source1.print()

        divider()

        val source2 = Observable.create<String> {
            it.onNext("hello")
            it.onComplete()
            it.onNext("world")
        }

        source2.print()

        divider()

        val source3 = Observable.create<String> {
            it.onNext("hello")
            it.onError(Exception("error !!!!"))
            it.onNext("world")
            it.onComplete()
        }

        source3.print()
    }

    /**
     * just() 함수는 넣은 아이템을 그대로 발행해준다.
     * null은 허용하지 않는다. 만약 빈 아이템을 넣어주고 싶다면 Observable.empty()를 사용한다.
     */
    @Test
    fun test03_just() {
        val source1 = Observable.just("hello", "world")
        source1.print()

        divider()

        val source2 = Observable.just("hello", Observable.empty<String>())
        source2.print()
    }

    /**
     * fromArray
     */
    @Test
    fun test04_fromArray() {
        val itemArray = arrayOf("a", "b", "c")
        val source1 = Observable.fromArray(itemArray) // 이렇게 하면 아이템에 Array 객체가 들어간다.
        source1.print()

        divider()

        val source2 = Observable.fromArray(*itemArray) // 이렇게 해야지 아이템 하나 하나가 들어간다.
        source2.print()
    }

    /**
     * fromIterable
     * List나 Set 같은 Iterable을 구현한 자료 구조 클래스 사용 가능
     */
    @Test
    fun test05_fromIterable() {
        val items = listOf("a", "b", "c")
        val source = Observable.fromIterable(items)
        source.print()
    }

    /**
     * Publisher fromPublisher
     */
    @Test
    fun test06_fromPublisher() {
        val publisher = Publisher<String> {
            it.onNext("a")
            it.onNext("b")
            it.onNext("c")
            it.onComplete()
        }
        val source = Observable.fromPublisher(publisher)
        source.print()
    }

    /**
     * Callable, fromCallable
     */
    @Test
    fun test07_fromCallable() {
        val callable = { "hello world" }
        val source = Observable.fromCallable(callable)
        source.print()
    }

    /**
     * Single은 단 하나의 아이템만 발행한다. 따라서 just에는 하나의 인자만 가능하다.
     */
    @Test
    fun test08_single_just() {
        Single.just("Hello world")
            .print()

        divider()

        Single.create<String> {
            it.onSuccess("hello world 2")
        }
            .print()

        divider()

        val source = Observable.just(1, 2, 3)
        source.toList().print()
    }
}