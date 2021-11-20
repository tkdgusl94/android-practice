package com.example.rx_sample.operation

import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * 옵저버를 결합하는 연산자들
 */
class CombinedTest {

    /**
     * 두 개의 소스가 있을 때 둘 중 마지막으로 나온 값이 나오게 된다.
     */
    @Test
    fun test01_combineLatest() {
        val source1 = Observable.create<Int> { emitter ->
            Thread {
                repeat(5) {
                    emitter.onNext(it)
                    Thread.sleep(1000)
                }
            }.start()
        }

        val source2 = Observable.create<String> { emitter ->
            Thread {
                Thread.sleep(500)
                emitter.onNext("a")
                Thread.sleep(700)
                emitter.onNext("b")
                Thread.sleep(2000)
                emitter.onNext("c")
            }.start()
        }
        Observable.combineLatest(source1, source2) { value1, value2 -> "$value1 $value2" }
            .subscribe { println(it) }

        Thread.sleep(5000)
    }

    /**
     * 발행 순서에 맞는 값으로 결합된다.
     * combineLatest는 가장 최근에 발행된 값끼리 결합되었다면,
     * zip은 한번 발행됐으면 다시 사용되지 않는다.
     */
    @Test
    fun test02_zip() {
        val source1 = Observable.create<Int> { emitter ->
            Thread {
                repeat(5) {
                    emitter.onNext(it)
                    Thread.sleep(1000)
                }
            }.start()
        }

        val source2 = Observable.create<String> { emitter ->
            Thread {
                Thread.sleep(500)
                emitter.onNext("a")
                Thread.sleep(700)
                emitter.onNext("b")
                Thread.sleep(2000)
                emitter.onNext("c")
            }.start()
        }

        Observable.zip(source1, source2) { a, b -> "$a $b" }
            .subscribe { println(it) }

        Thread.sleep(5000)
    }
}
