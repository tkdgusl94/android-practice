package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

/**
 * 옵저버를 결합하는 연산자들
 */
class CombinedTest {

    @Test
    fun test01_combineLatest() {
        val source1 = Observable.just(1, 2, 3)
        val source2 = Observable.just("a", "b", "c")

//        Observable.combineLatest()
    }
}