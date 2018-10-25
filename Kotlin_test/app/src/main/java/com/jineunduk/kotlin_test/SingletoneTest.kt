package com.jineunduk.kotlin_test

// object 형태로 만들기만 하면 static의 single tone이 만들어진다.
// INSTANCE로 접근할 수도 있고
// 맴버변수를 lay init으로 사용시에 생성해서 불필요한 메모리 점유 및 동작을 막을 수 있다.
// lazy는 기본적으로 Synchronized로 동작하고 싫다면. lazy(LazyThreadSafetyMode.NONE)을 넣어주면 된다.

object SingletoneTest {
    val convertTest : ConvertTest by lazy(LazyThreadSafetyMode.NONE) {
        ConvertTest.getInstance()
    }
}