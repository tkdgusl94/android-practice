package com.example.dagger2_sample.injection

import com.example.dagger2_sample.injection.module.MyModule1
import dagger.Component

@Component(
    modules = [
        MyModule1::class,
        /**
         * 하나의 컴포넌트는 여러개의 모듈을 사용한다.
         * 컴포넌트 안에 정의된 메소드들의 반환값은 모듈에서 제공된다.
         * 만약 모듈안에서 같은 타입의 반환값이 여러개가 존재하다면 컴파일 에러가 발생한다.
         */
//        DuplicationModule::class
    ])
interface MyComponent1 {
    fun getHelloWorld(): String
}