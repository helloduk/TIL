package com.jineunduk.kotlin_test

import android.util.Log
import android.widget.Button
import android.widget.Toast


//Single tone 만들기, Primary 생성자를 private로 변경
class ConvertTest private constructor() {

    init {
        // 초기화가 됐는지 확인하는 방법
        if (this::lateInit.isInitialized){
            println("$lateInit")
        }
    }

    // lateinit을 쓰면 초기화를 나중에 해주어도 된다.
    private lateinit var lateInit : String

    // 초기화를 호출될 때 해준다. val인 경우만 설정가능함.
    private val helloLazy : String by lazy {
        "hello"
    }

    internal var mButton: Button? = null

    // 람다식에서 매개변수가 하나이면 it으로 사용한다.
    internal fun setListener() {
        mButton!!.setOnClickListener { Log.i(TAG, it.toString()) }
    }

    // annotation으로 synchronize 걸어준다
    @Synchronized
    fun sayHaHaHa() {
        Log.i(TAG, "sayHaHaHa")
    }

    // companion object로 getInstance 구현
    companion object {
        val TAG = "ConvertTest"
        private var sMe : ConvertTest? = null

        // also는 그 결과물을 return도 하고 또 다른 일을 하고 싶을때
        // synchronized를 걸어주어야 싱글톤이 생성되는 순간에 다른 쓰레드에서 생성을 요청해서,
        // 싱글톤이지만 객체가 두개 이상생성되는 현상을 막을 수 있다.
        @Synchronized
        fun getInstance() : ConvertTest {
            return sMe ?: synchronized(ConvertTest::class.java){
                ConvertTest().also{
                sMe = it
            }}

        }
    }
}
