@file:JvmName("Utils")

package com.jineunduk.kotlin_test

import android.content.Context
import android.content.Intent

//Class 정의 없이 함수만 정의 가능
//Java에서 쓰게 하기 위해서는 @JvmName annotation 추가 필

//Extention 기능. 기존 Context class에 startActivity(String) api를 추가 한다.
fun Context.startActivity(name : String) {
//    val intent :Intent = Intent(this, Main3Activity::class.java)
//    intent.putExtra("key", name)
//    this.startActivity(intent)

    // 윗 내용 줄임
    // apply {} 일반적으로 생성과 동시에 어떤 값을 초기화 하고자 할때 사용
    // let(it을 받는다)과 run(this를 받는다.) 어떤 번수에 접근해서 어떤 작업을 할때 많이 사용
    startActivity(Intent(this, Main3Activity::class.java).apply {
        this.putExtra("key", name)
    })
}