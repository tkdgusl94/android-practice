package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * 옵저버를 만드는 함수들
 * defer, empty, never, interval, range, timer
 */
class CreationTest {

    /**
     * defer 연산자는 옵저버가 구독할때까지 옵저버의 생성을 지연시킨다.
     */
    @Test
    fun test01_defer() {
        val source = Observable.just(System.currentTimeMillis())
        val deferSource = Observable.defer {
            Observable.just(System.currentTimeMillis())
        }

        println("#1 now: ${System.currentTimeMillis()}")

        try {
            Thread.sleep(500)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        println("#2 now: ${System.currentTimeMillis()}")

        source.subscribe {
            println("#1 time: $it")
        }

        deferSource.subscribe {
            println("#2 time: $it")
        }
    }

    @Test
    fun test02_empty() {
        Observable.empty<String>()
            .doOnTerminate { println("empty 종료") }
            .subscribe { println("data: $it") }
    }

    @Test
    fun test03_never() {
        Observable.never<String>()
            .doOnTerminate { println("never 종료") }
            .subscribe { println("data: $it") }
    }

    @Test
    fun test04_interval() {
        val disposable = Observable.interval(1, TimeUnit.SECONDS)
            .subscribe { println(it) }

        Thread.sleep(3000)

        disposable.dispose()
    }

    @Test
    fun test05_range() {
        Observable.range(1, 3)
            .subscribe { println(it) }
    }

    /**
     * 특정 시간 동안 지연시킨 뒤 아이템을 발행한다.
     */
    @Test
    fun test06_timer() {
        val source = Observable.timer(1, TimeUnit.SECONDS)
        println("구독! ${System.currentTimeMillis()}")
        source.subscribe { println("실행! ${System.currentTimeMillis()}") }
        Thread.sleep(1500)
    }
}
