package com.jineunduk.kotlin_test

import android.util.Log
import android.widget.Button
import org.junit.Test
import com.google.gson.annotations.SerializedName

//sealed class : 다형성을 표현, 하나의 함수 인자에 대표 형으로 사용해서 각각의 형태에 맞게 동작하는 하나의 함수 정의 가능
// class 밖에 정의 되어야한다.
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()


class UnitTest {
    @Test
    fun whenTest() {
        out("AAA")
        out(13)
        out(22.2)
        out(12.0f)
    }

    private fun out(an: Any?) {
        when (an) {
            is Int -> print("Int" + an)
            is String -> print("String" + an)
            is Double -> print("Double" + an)
            else -> println("Nothing")
        }
    }

    @Test
    fun elvisOperator() {
        val b: String? = null
        // 아래와 같은 if else가 간단하게 Elvis Operator를 통해 표
        //val l : Int = if(b != null) b.length  else -1
        val l = b?.length ?: -1

        // b가 null이 아니여야만 할때
        //val k = b!!.length
    }

    @Test
    fun classTest() {
        ClassTest("hello", 12)
        ClassTest("hello2")
    }

    // @JvmOverloads : Java에서도 호출할 수 있게
    // constructor 생략가능
    class ClassTest @JvmOverloads constructor(val name: String, val age: Int) {
        //생성자에서 자동으로 불리는 function
        init {
            println("name : $name age : $age")
        }

        //primary 생성자가 아닌 secondary에서는 반드시 primary를 호출해줘야한다.
        constructor(name: String) : this(name, 10)

    }

    //다중 상속, 동일 api라도 interface의 api를 사용할 수 있다.
    interface AA {
        fun init() {
            println("AA")
        }
    }

    abstract class BB {
        open abstract fun init()
    }

    class CC : AA, BB() {

        override fun init() {
            super<AA>.init()
        }
    }

    // data class getter/setting, equals, hashcode, toString등 알아서 생성

    @Test
    fun dataClassTest() {
        var user = User()
        user.id = 10
        println("user + $user")
    }

    data class User(var id: Long = 0,
                    var name: String? = null,
                    var email: String? = null)

    data class UserItem(@field:SerializedName("login") val login: String,
                        @field:SerializedName("id") val id: Int,
                        @field:SerializedName("avatar_url") val avatralUrl: String) {

    }

    //sealed class : 다형성을 표현, 하나의 함수 인자에 대표 형으로 사용해서 각각의 형태에 맞게 동작하는 하나의 함수 정의 가능
    fun eval(expr: Expr): Double =
            when (expr) {
                is Const -> expr.number
                is Sum -> eval(expr.e1) + eval(expr.e2)
                NotANumber -> Double.NaN
            }


    @Test
    fun sealedTest() {
        println(eval(Sum(Const(0.0), Const(1.0))))
    }

    //singletone
    object Eager

    //Kotln Lambdas
    // return 생략가능
    // parameter가 하나면 it으로 대체가능
    @Test
    fun lambdaTest() {
        var btn: Button? = null
        btn?.setOnClickListener {
            Log.d("Sample", "view $it")
        }

    }

    // Function variable,
    var onclick: (position: Int, a : String) -> Unit = { position, a -> println("position $position $a")}

    @Test
    fun functionVariableTest(){
        onclick.invoke(100, "functionVariable")
    }

    // High Order Functions : 함수를 인자나 리턴값으로 사용하는 함
    fun highterOrder(body: (Int, Int) -> Int) = body(20,10)
    fun sum(a: Int, b: Int) = a + b
    fun minus(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b
    fun division(a: Int, b: Int) = a / b

    @Test
    fun higherOrderTest() {
        println(highterOrder(::sum))
        println(highterOrder(::minus))
        println(highterOrder(::multiply))
        println(highterOrder(::division))
    }
}
