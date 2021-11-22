package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class ErrorTest {

    /**
     * error가 생기면 그 뒤에 있는 발행물은 발행되지 않는다. 스트림을 종료시킨다.
     */
    @Test
    fun test01_error() {
        Observable.just("1", "2", "a", "3")
            .map { it.toInt() }
            .subscribe({
                println(it)
            }, { error ->
                println(error)
            })
    }

    @Test
    fun test02_onErrorReturn() {
        Observable.just("1", "2", "a", "3")
            .map { it.toInt() }
            .onErrorReturn { -1 }
            .subscribe { println(it) }
    }

    /**
     * 에러가 발생하면 기존 스트림을 종료시키고, 다른 Observable 소스로 스트림을 대체한다.
     */
    @Test
    fun test03_onErrorResumeNext() {
        Observable.just("1", "2", "a", "3")
            .map { it.toInt() }
            .onErrorResumeNext {
                Observable.just(100, 200, 300)
            }
            .subscribe { println(it) }
    }

    @Test
    fun test04_retry() {
        Observable.just("1", "2", "a", "3")
            .map { it.toInt() }
            .retry(2)
            .subscribe { println(it) }
    }
}