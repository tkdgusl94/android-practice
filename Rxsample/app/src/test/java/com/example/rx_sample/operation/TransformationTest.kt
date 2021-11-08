package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

/**
 * 옵저버의 아이템을 변형시키는 함수들
 * map, flatMap, buffer, scan, groupBy
 */
class TransformationTest {

    /**
     * 10
     * 20
     * 30
     */
    @Test
    fun test01_map() {
        Observable.just(1, 2, 3)
            .map { it * 10 }
            .subscribe { println(it) }
    }

    /**
     * 10
     * 20
     * 20
     * 40
     * 30
     * 60
     */
    @Test
    fun test02_flatMap() {
        Observable.just(1, 2, 3)
            .flatMap {
                Observable.just(it * 10, it * 20)
            }
            .subscribe { println(it) }
    }

    /**
     * 버퍼 데이터 발행
     * 0
     * 1
     * 2
     * 버퍼 데이터 발행
     * 3
     * 4
     * 5
     * 버퍼 데이터 발행
     * 6
     * 7
     * 8
     * 버퍼 데이터 발행
     * 9
     */
    @Test
    fun test03_buffer() {
        Observable.range(0, 10)
            .buffer(3)
            .subscribe { list ->
                println("버퍼 데이터 발행")
                list.forEach { println(it) }
            }
    }

    /**
     * 1
     * 2
     * 6
     * 24
     * 120
     */
    @Test
    fun test04_scan() {
        Observable.range(1, 5)
            .scan { x, y -> x * y }
            .subscribe { println(it) }
    }

    /**
     * group key: A
     * A1
     * B1
     * group key: B
     * A11
     * B11
     */
    @Test
    fun test05_groupBy() {
        Observable.just("A1", "B1", "A11", "B11")
            .groupBy {
                return@groupBy if (it.length == 2)
                    "A"
                else
                    "B"
            }
            .subscribe { group ->
                println("group key: ${group.key}")
                group.subscribe {
                    println(it)
                }
            }
    }
}
