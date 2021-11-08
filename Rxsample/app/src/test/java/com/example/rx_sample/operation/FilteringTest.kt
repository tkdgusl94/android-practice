package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 옵저버의 아이템을 필터링 시켜주는 함수들
 * debounce, distinct, elementAt, filter, sample, skip, take, all, amb
 */
class FilteringTest {

    /**
     * 1
     * 4
     * 6
     */
    @Test
    fun test01_debounce() {
        Observable.create<Int> {
            it.onNext(1)
            Thread.sleep(100)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)
            Thread.sleep(100)
            it.onNext(6)
        }
            .debounce(10, TimeUnit.MILLISECONDS)
            .subscribe { println(it) }

        Thread.sleep(100)
    }

    /**
     * 1
     * 2
     * 3
     */
    @Test
    fun test02_distinct() {
        Observable.just(1, 2, 3, 2, 1)
            .distinct()
            .subscribe { println(it) }
    }

    /**
     * 3
     */
    @Test
    fun test03_elementAt() {
        Observable.just(1, 2, 3, 4)
            .elementAt(2)
            .subscribe { println(it) }
    }

    /**
     * 22
     * 30
     * 56
     */
    @Test
    fun test04_filter() {
        Observable.just(2, 22, 30, 56, 1)
            .filter { it > 10 }
            .subscribe { println(it) }
    }

    /**
     * 1
     * 5
     * 7
     * 11
     */
    @Test
    fun test05_sample() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .sample(300, TimeUnit.MILLISECONDS)
            .subscribe { println(it) }
        Thread.sleep(1500)
    }

    /**
     * 3
     * 4
     */
    @Test
    fun test06_skip() {
        Observable.just(1, 2, 3, 4)
            .skip(2)
            .subscribe { println(it) }
    }

    /**
     * 1
     * 2
     */
    @Test
    fun test06_take() {
        Observable.just(1, 2, 3, 4)
            .take(2)
            .subscribe { println(it) }
    }

    /**
     * true
     */
    @Test
    fun test07_all() {
        Observable.just(1, 2, 3, 4)
            .all { it < 5 }
            .subscribe { result, throwable ->
                println(result)
            }
    }

    /**
     * 여러 개의 옵저버들을 동시에 구독하고 가장 먼저 아이템을 발행하는 옵저버를 선택한다.
     * 1
     * 2
     * 3
     */
    @Test
    fun test08_amb() {
        Observable.amb(
            listOf(
                Observable.just(20, 40, 60)
                    .delay(100, TimeUnit.MILLISECONDS),
                Observable.just(1, 2, 3),
                Observable.just(100, 200, 300)
            )
        )
            .subscribe { println(it) }
    }
}

