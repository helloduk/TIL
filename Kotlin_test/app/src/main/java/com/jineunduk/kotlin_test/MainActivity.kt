package com.jineunduk.kotlin_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SingletoneTest.convertTest.sayHaHaHa()

        // Java에서 static 접근시 Companion을 써야하는데,
        // 이를 쓰고 싶지 않다면 @JvmStatic annotation을 붙여줘야한다.
        ConvertTest.getInstance().sayHaHaHa()

        // Extention으로 추가한 API 사용
        this.startActivity("key")
    }
}
