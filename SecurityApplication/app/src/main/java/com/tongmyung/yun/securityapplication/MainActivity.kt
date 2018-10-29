package com.tongmyung.yun.securityapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var P10 = arrayOf("0","0","0","0","0","0","0","0","0","0") //P10 초기화

    var binary: String? = null // Interger.toBinaryString 해주기위해 만들어줌

    var K1 = arrayOf("0","0","0","0","0","0","0","0") //K1 초기화
    var K2 = arrayOf("0","0","0","0","0","0","0","0") //K2 초기화
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var hashmap = HashMapadd()
        hashmap.S1_map_add()
        hashmap.S0_map_add()



        for(i in 0..3)
            println("S0_map 의 안에 내용 ${hashmap.S0_map.get(i)}")
        for(i in 0..3)
            println("S1_map 의 내용 ${hashmap.S1_map.get(i)}")

        button_security.setOnClickListener { v ->
            binaryChange()
        }
    }
    fun binaryChange(){ //버튼 클릭시 서브키 입력  연산
        try {
            println("버튼 클릭")
            var subkey = input_subkey.text
            var integer = Integer.parseInt(subkey.toString())
            var temp = Integer.toBinaryString(integer)
            var int_temp = Integer.valueOf(temp)
            binary = String.format("%010d",int_temp)
            println(binary)
            P10_make()
            println("P10(key) = ${P10[0]} ${P10[1]} ${P10[2]} ${P10[3]} ${P10[4]} ${P10[5]} ${P10[6]} ${P10[7]} ${P10[8]} ${P10[9]}")
            shift_calculate()
            println("LS-1 = ${P10[0]} ${P10[1]} ${P10[2]} ${P10[3]} ${P10[4]} ${P10[5]} ${P10[6]} ${P10[7]} ${P10[8]} ${P10[9]}")
            K1_make()
            println("K1 = ${K1[0]} ${K1[1]} ${K1[2]} ${K1[3]} ${K1[4]} ${K1[5]} ${K1[6]} ${K1[7]}")
            shift_calculate() // 두번불렀으니 두번 shift 연산
            shift_calculate()
            println("LS-2 = ${P10[0]} ${P10[1]} ${P10[2]} ${P10[3]} ${P10[4]} ${P10[5]} ${P10[6]} ${P10[7]} ${P10[8]} ${P10[9]}")
            K2_make()
            println("K2 = ${K2[0]} ${K2[1]} ${K2[2]} ${K2[3]} ${K2[4]} ${K2[5]} ${K2[6]} ${K2[7]}")
//            var binary = Integer.toBinaryString(integer)
//            var int = Integer.valueOf(binary)
//            binary = String.format("%010d", int)
//            println("binary : $")
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun K1_make(){ //K1 만드는 함수
        K1[0] = P10!![5]
        K1[1] = P10!![2]
        K1[2] = P10!![6]
        K1[3] = P10!![3]
        K1[4] = P10!![7]
        K1[5] = P10!![4]
        K1[6] = P10!![9]
        K1[7] = P10!![8]
    }

    fun K2_make(){ //K2 만드는함수
        K2[0] = P10!![5]
        K2[1] = P10!![2]
        K2[2] = P10!![6]
        K2[3] = P10!![3]
        K2[4] = P10!![7]
        K2[5] = P10!![4]
        K2[6] = P10!![9]
        K2[7] = P10!![8]
    }
    fun P10_make(){ //P10 만드는 함수
        P10[0] = binary!![2].toString()
        P10[1] = binary!![4].toString()
        P10[2] = binary!![1].toString()
        P10[3] = binary!![6].toString()
        P10[4] = binary!![3].toString()
        P10[5] = binary!![9].toString()
        P10[6] = binary!![0].toString()
        P10[7] = binary!![8].toString()
        P10[8] = binary!![7].toString()
        P10[9] = binary!![5].toString()
    }
    fun shift_calculate(){ //쉬프트 연산 함수
        var temp = P10[0]
        for(i in 1..5){
            P10[i-1] = P10[i]
        }
        P10[4] = temp
        temp = P10[5]
        for(i in 6..9){
            P10[i-1] = P10[i]
        }
        P10[9] = temp
    }
}
