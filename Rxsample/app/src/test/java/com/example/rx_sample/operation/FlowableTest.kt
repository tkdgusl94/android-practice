package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class FlowableTest {

    @Test
    fun test01() {
        Flowable.range(1, Int.MAX_VALUE)
            .doOnNext { println("아이템 발행: $it") }
            .observeOn(Schedulers.io())
            .subscribe {
                Thread.sleep(100)
                println("아이템 소비: $it")
            }

        Thread.sleep(30000)
    }
}