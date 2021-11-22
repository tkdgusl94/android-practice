package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class SchedulerTest {

    /**
     * Test worker : 0
     * Test worker : 0
     * Test worker : 1
     * Test worker : 1
     * Test worker : 2
     * Test worker : 2
     */
    @Test
    fun test01() {
        Observable.create<Int> { emitter ->
            repeat(3) {
                println("${Thread.currentThread().name} : $it")
                emitter.onNext(it)
                Thread.sleep(100)
            }

            emitter.onComplete()
        }
            .subscribe {
                println("${Thread.currentThread().name} : $it")
            }
    }

    /**
     * RxCachedThreadScheduler-1 : 0
     * RxCachedThreadScheduler-1 : 0
     * RxCachedThreadScheduler-1 : 1
     * RxCachedThreadScheduler-1 : 1
     * RxCachedThreadScheduler-1 : 2
     * RxCachedThreadScheduler-1 : 2
     * subscribeOn() 연산자는 옵저버블 소스에 어떤 스케줄러를 사용하여 아이템을 발행할지 알려준다.
     */
    @Test
    fun test02() {
        Observable.create<Int> { emitter ->
            repeat(3) {
                println("${Thread.currentThread().name} : $it")
                emitter.onNext(it)
                Thread.sleep(100)
            }

            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .subscribe {
                println("${Thread.currentThread().name} : $it")
            }

        Thread.sleep(500)
    }

    @Test
    fun test03_observeOn() {
        Observable.create<Int> { emitter ->
            repeat(3) {
                println("#Subs -> ${Thread.currentThread().name} : $it")
                emitter.onNext(it)
                Thread.sleep(100)
            }

            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                println("#Obsv -> ${Thread.currentThread().name} : $it")
            }

        Thread.sleep(500)
    }
}
