package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class DoOnTest {

    /**
     * i: 1
     * isOnNext: true
     * isOnComplete: false
     * isOnError: false
     * subscribed: 1
     * i: 2
     * isOnNext: true
     * isOnComplete: false
     * isOnError: false
     * subscribed: 2
     * i: 3
     * isOnNext: true
     * isOnComplete: false
     * isOnError: false
     * subscribed: 3
     * i: null
     * isOnNext: false
     * isOnComplete: true
     * isOnError: false
     */
    @Test
    fun test01_doOnEach() {
        Observable.just(1, 2, 3)
            .doOnEach { notification ->
                println("i: ${notification.value}")
                println("isOnNext: ${notification.isOnNext}")
                println("isOnComplete: ${notification.isOnComplete}")
                println("isOnError: ${notification.isOnError}")
            }
            .subscribe {
                println("subscribed: $it")
            }
    }

    @Test
    fun test02_doOnNext() {
        Observable.just(1, 2, 3)
            .doOnNext {
                if (it > 1) {
                    throw IllegalArgumentException()
                }
            }
            .subscribe {
                println(it)
            }
    }

    /**
     * doOnSubscribe()는 구독 시마다 콜백을 받을 수 있다.
     */
    @Test
    fun test03_doOnSubscribe() {
        Observable.just(1, 2, 3)
            .doOnSubscribe {
                println("구독 시작!")
            }
            .subscribe { println(it) }
    }

    @Test
    fun test04_doOnComplete() {
        Observable.just(1, 2, 3)
            .doOnComplete {
                println("완료 !")
            }
            .subscribe { println(it) }
    }

    @Test
    fun test05_doOnError() {
        Observable.just(2, 1, 0)
            .map { 10 / it }
            .doOnError { println("error !") }
            .subscribe { println(it) }
    }

    /**
     * doOnTerminate()는 옵저버블이 종료될 때 호출된다.
     * 에러가 발생해도 호출된다.
     */
    @Test
    fun test06_doOnTerminate() {
        Observable.just(2, 1, 0)
            .map { 10 / it }
            .doOnComplete { println("완료 !") }
            .doOnTerminate { println("terminate") }
            .subscribe { println(it) }
    }

    /**
     * dispose() 메소드로 인해 폐기되는 경우 호출된다.
     */
    @Test
    fun test07_doOnDispose() {
        val src = Observable.interval(500, TimeUnit.MILLISECONDS)
            .doOnDispose { println("do on dispose") }

        val disposable = src.subscribe { println(it) }

        Thread.sleep(1100)

        disposable.dispose()
    }
}
