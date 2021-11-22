package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Test

class SubjectTest {

    /**
     * PublishSubject는 Observable과 Observer를 모두 구현한 구현체이다.
     * 발행과 구독을 모두 Subject 객체를 통해 한다.
     * Subject는 Hot Observable이다.
     */
    @Test
    fun test01_publishSubject() {
        val src = PublishSubject.create<String>()

        src.subscribe({
            println("A -> $it")
        }, {
            println("A -> error !!!")
        }, {
            println("A -> complete")
        })

        src.subscribe({
            println("B -> $it")
        }, {
            println("B -> error !!!")
        }, {
            println("B -> complete")
        })

        src.onNext("hello")
        src.onNext("world")
        src.onComplete()
    }

    @Test
    fun test02() {
        val src1 = Observable.just(1, 2, 3)
        val src2 = Observable.just(4, 5, 6)
        val subject = PublishSubject.create<String>()

        subject.subscribe {
            println(it)
        }

        src1.map { "A -> $it" }
            .subscribe(subject)

        src2.map { "B -> $it" }
            .subscribe(subject)

        Thread.sleep(1000)
    }
}