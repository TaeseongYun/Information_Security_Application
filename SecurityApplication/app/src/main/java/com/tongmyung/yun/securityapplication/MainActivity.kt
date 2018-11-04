package com.tongmyung.yun.securityapplication

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/*지금 까지 입력한값 영어만 toByte()해주어서 P를 구했다
* 한글은 2Byte인데 어떻게 8 바이트씩 나누어서 표현?
* 영어 특수문자 는 거의 확인 다 되었음*
* 지금 EP와 IP XOR 까지 구현 완료 했음 이제 뒤에 S박스를 하는것만 남음
* 2018-11-01 S박스 구현 완료*/
//SBox 계산 하는 함수 즉 F(R,SK)에서 확장시켜주고 SBox값 찾아가는것 까지 구현 완료
//이제 스위치 하는 것 남았음
//암호화 작성완료 복호화만 남았음!
class MainActivity : AppCompatActivity() {
    val P10_Index = arrayOf(3, 5, 2, 7, 4, 10, 1, 9, 8, 6)
    val P8_Index = arrayOf(6, 3, 7, 4, 8, 5, 10, 9)
    val IP_Index = arrayOf(2, 6, 3, 1, 4, 8, 5, 7)
    val EP_Index = arrayOf(7, 4, 5, 6, 5, 6, 7, 4)
    val IP_1_Index = arrayOf(4, 1, 3, 5, 7, 2, 8, 6)
    var F_FUN = arrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    val S_Box0_row = arrayOf(1, 4) //S_BOX0 가로  상단 인덱스
    val S_Box0_column = arrayOf(2, 3) //S_BOX0 세로 좌측 인덱스
    val S_Box1_row = arrayOf(5, 8) //S_Box1 가로 상단 인덱스
    val S_Box1_column = arrayOf(6, 7) //S_Box 1 세로 좌측 인덱스
    val LEFT = arrayOf(1, 2, 3, 4)
    val RIGHT = arrayOf(5, 6, 7, 8)
    val P4 = arrayOf(2, 4, 3, 1)
    val switch_FromLeftToRight = arrayOf(5, 6, 7, 8) //왼쪽것을 오른쪽으로 옮기는 변수
    val switch_FromRightToLeft = arrayOf(1, 2, 3, 4) //오른쪽것을 왼쪽으로 옮기는 변수
    var P10 = arrayOf("0", "0", "0", "0", "0", "0", "0", "0", "0", "0") //P10 값 초기화
    var subkey_Binary: String? = null // Interger.toBinaryString 해주기위해 만들어줌
    var plainText: String? = null //평서문을 넣어주는곳
    var plainText_Binary: String? = null // 입력한 평서문 값을 toByte() 한 결과값을 여기다가 넣어줌 즉 P값
    var IP = arrayOf("0", "0", "0", "0", "0", "0", "0", "0")//IP(초기 순열함수)값을  초기화 P를 토대로 다시 초기화 시켜줌
    var IP_1 = arrayOf("0", "0", "0", "0", "0", "0", "0", "0")//IP-1(최종 순열함수)값을 초기화 P를 토대로 다시 초기화
    var E_P = arrayOf("0", "0", "0", "0", "0", "0", "0", "0")
    var K1 = arrayOf("0", "0", "0", "0", "0", "0", "0", "0") //K1 초기화
    var K2 = arrayOf("0", "0", "0", "0", "0", "0", "0", "0") //K2 초기화
    var security_sentence: String? = null //암호화 시킨 문장
    var security_sentence_Binary: String? = null
    var recovery_Sentence: ArrayList<String>? = null //복구화 시킨 문장
    var hashMap: HashMapadd? = null
    var binarySBoxZeroResult: String? = null
    var binarySBoxOneResult: String? = null
    var SBoxResult = arrayOf("0", "0", "0", "0") //P4 다끝나고 결과값 저장
    var SBoxResultTemp = arrayOf("0", "0", "0", "0") //P4해주기전에 잠깐 결과값만 저장해두는 배열
    val SBoxIndex = arrayOf(2, 3)
    var fkResultLeft = arrayOf("0", "0", "0", "0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hashMap = HashMapadd()
        hashMap?.S0_map_add()
        hashMap?.S1_map_add()


        for (i in 0..3)
            println("S0_map 의 안에 내용 ${hashMap?.S0_map?.get(i)}")
        for (i in 0..3)
            println("S1_map 의 내용 ${hashMap?.S1_map?.get(i)}")

        button_security.setOnClickListener { v ->
            //            서브키값 체크 하는 함수
            security_sentence = String()
            subkey_check()
//            P8, P10, K1, K2, P, IP, IP-1 계산하는 함수
            binaryChange()

            textView_security_sentence.text = security_sentence.toString()
        }
        button_insecurity.setOnClickListener {
            decryption()
        }
        button_reset.setOnClickListener { v ->
            plainTextReset()
        }
    }

    fun binaryChange() { //버튼 클릭시 서브키 입력  연산
        try {
            enable_plainText()
            subkey_Binary_make()
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
            input_security_key()
            println("K2 = ${K2[0]} ${K2[1]} ${K2[2]} ${K2[3]} ${K2[4]} ${K2[5]} ${K2[6]} ${K2[7]}")
            plainText = input_normalKey.text.toString()
            make_P()
//            make_IP()
//            println("IP = ${IP[0]} ${IP[1]} ${IP[2]} ${IP[3]} ${IP[4]} ${IP[5]} ${IP[6]} ${IP[7]}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun subkey_Binary_make() {
        var subkey = input_subkey.text
        var subkey_integer = Integer.parseInt(subkey.toString())
        var subkey_binary_temp = Integer.toBinaryString(subkey_integer)
        var subkey_binary_tempToint = Integer.valueOf(subkey_binary_temp)
        subkey_Binary = String.format("%010d", subkey_binary_tempToint)
        println("subkey_Binary : $subkey_Binary")
    }

    fun K1_make() { //K1 만드는 함수
        for (i in K1.indices)
            K1[i] = P10[P8_Index[i] - 1]
    }

    fun K2_make() { //K2 만드는함수
        for (i in K2.indices)
            K2[i] = P10[P8_Index[i] - 1]
    }

    fun P10_make() { //P10 만드는 함수
        for (i in P10.indices)
            P10[i] = subkey_Binary!![P10_Index[i] - 1].toString()
    }

    fun shift_calculate() { //쉬프트 연산 함수
        var temp = P10[0]
        for (i in 1..5) {
            P10[i - 1] = P10[i]
        }
        P10[4] = temp
        temp = P10[5]
        for (i in 6..9) {
            P10[i - 1] = P10[i]
        }
        P10[9] = temp
    }

    fun input_security_key() { //textView 안에 넣어주는 함수
        var P10_list = ArrayList<String>()
        var K1_list = ArrayList<String>()
        var K2_list = ArrayList<String>()
        for (i in P10.indices)
            P10_list.add(P10[i])

        for (j in K1.indices)
            K1_list.add(K1[j])

        for (x in K2.indices)
            K2_list.add(K2[x])
        textView_P10.text = P10_list.toString()
        textView_K1.text = K1_list.toString()
        textView_K2.text = K2_list.toString()
    }

    fun make_P() { //이 함수 안에 EP 만드는것 있고 IP 만드는것 있음
        for (i in plainText?.indices!!) {
            var P_integer = plainText!![i].toByte()
            println("plainText[$i] = ${plainText!![i]}")
            var P_temp = Integer.toBinaryString(P_integer.toInt())
//            println("P_temp = plainText.toByte = ${P_temp}")
            println("plainText.getByte = ${plainText!![i].toByte()}")
            var P_temp_To_Int = Integer.valueOf(P_temp)
            plainText_Binary = String.format("%08d", P_temp_To_Int)
            println("plainText_Binary : ${plainText_Binary}")
            make_IP()
            println("IP index $i = ${IP[0]} ${IP[1]} ${IP[2]} ${IP[3]} ${IP[4]} ${IP[5]} ${IP[6]} ${IP[7]}")
            E_P_make()//처음 fk 했을때 E_P 확장 시켜주기
            println("EP index $i = ${E_P[0]} ${E_P[1]} ${E_P[2]} ${E_P[3]} ${E_P[4]} ${E_P[5]} ${E_P[6]} ${E_P[7]}")
            exclusiveOR_EP_K1()
            println("F_Fun $i = ${F_FUN[0]} ${F_FUN[1]} ${F_FUN[2]} ${F_FUN[3]} ${F_FUN[4]} ${F_FUN[5]} ${F_FUN[6]} ${F_FUN[7]}")
            S_Box_calculator()
            switchFun()
            E_P_make()//두번째 K2로 할때 확장
            exclusiveOR_EP_K2()
            S_Box_calculator()
            security_IP_1()
        }
    }

    fun make_IP() { //IP 만드는 함수
        for (i in IP.indices)
            IP[i] = plainText_Binary!![IP_Index[i] - 1].toString()
    }
    fun make_security_IP(){
        for(i in IP.indices)
            IP[i] = security_sentence_Binary!![IP_Index[i] - 1].toString()
    }
    fun subkey_check() {
        var range = input_subkey.text
        var range_Integer = Integer.parseInt(range.toString())
        if (range_Integer > 1023 || range_Integer < 0) {
//            서브키 입력값이 1024 이상 이면 알람 뜨게 만들어줌
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("서브키 값 범위초과")
                    .setMessage("서브키 값은 0~1023까지 입니다. 다시 입력해주세요\n 확인을 누르면 초기화가 됩니다.")
                    .setIcon(R.drawable.danger_icon)
                    .setPositiveButton("확인", { dialog: DialogInterface?, which: Int ->
                        input_subkey.text.clear()
                        input_normalKey.text.clear()
                    })
                    .setNegativeButton("취소", { dialog: DialogInterface?, which: Int ->
                        dialog?.dismiss()
                    })
                    .show()
        }
    }

    //    암호화 시작됬으면 수정 못하게 막아둠
    fun enable_plainText() {
        input_subkey.isEnabled = false
        input_normalKey.isEnabled = false
    }

    //    초기화 버튼 눌렸을때 반응 하는 함수
    fun plainTextReset() {
        input_subkey.isEnabled = true
        input_normalKey.isEnabled = true
        input_subkey.text.clear()
        input_normalKey.text.clear()
        textView_P10.text = "HERE IS P10_RESULT"
        textView_K1.text = "HERE IS K1_RESULT"
        textView_K2.text = "HERE IS K2_RESULT"
        Toast.makeText(this, "초기화가 완료 되었습니다", Toast.LENGTH_SHORT).show()
    }

    fun E_P_make() { //EP 만들어주는 함수
        for (i in E_P.indices)
            E_P[i] = IP[EP_Index[i]]
    }

    fun exclusiveOR_EP_K1() { //함수 F()에서 EP와 K1 XOR 하는 함수
        for (i in E_P.indices) {
            if (E_P[i] == K1[i])
                F_FUN[i] = 0
            else
                F_FUN[i] = 1
        }
    }

    fun exclusiveOR_EP_K2() {
        for (i in E_P.indices) {
            if (E_P[i] == K2[i])
                F_FUN[i] = 0
            else
                F_FUN[i] = 1
        }
    }

    fun S_Box_calculator() { //SBox 계산 하는 함수 즉 F(R,SK)에서 확장시켜주고 SBox값 찾아가는것 까지 구현 완료
        var s_Box_Zero_row = arrayOf(0, 0)
        var s_Box_Zero_column = arrayOf(0, 0)
        var s_Box_One_row = arrayOf(0, 0)
        var s_Box_One_column = arrayOf(0, 0)
        for (i in s_Box_Zero_column.indices) {
            s_Box_Zero_row[i] = F_FUN[S_Box0_row[i] - 1]
            println("s_Box_Zero_row(가로 인덱스) $i = ${s_Box_Zero_row[i]}")
            s_Box_Zero_column[i] = F_FUN[S_Box0_column[i] - 1]
            println("s_Box_Zero_column(세로인덱스) $i = ${s_Box_Zero_column[i]}")
            s_Box_One_row[i] = F_FUN[S_Box1_row[i] - 1]
            println("s_Box_One_column(가로 인덱스) $i = ${s_Box_One_row[i]}")
            s_Box_One_column[i] = F_FUN[S_Box1_column[i] - 1]
            println("s_Box_One_column(세로 인덱스) $i = ${s_Box_One_column[i]}")
        }
        var s_Box_ZeroRowResult = (s_Box_Zero_row[0] * 2) + (s_Box_Zero_row[1] * 1)
        var s_Box_ZeroColumnResult = (s_Box_Zero_column[0] * 2) + (s_Box_Zero_column[1] * 1)
        var s_BoxOneRowResult = (s_Box_One_row[0] * 2) + (s_Box_One_row[1] * 1)
        var s_BoxOneColumnResult = (s_Box_One_column[0] * 2) + (s_Box_One_column[1] * 1)
        println("s_Box_ZeroRowResult = $s_Box_ZeroRowResult")
        println("s_Box_ZeroColumnResult = $s_Box_ZeroColumnResult")
        println("s_Box_OneRowResult = $s_BoxOneRowResult")
        println("s_Box_OneColumnResult = $s_BoxOneColumnResult")
        var rowResult_SBoxZero = hashMap?.S0_map?.get(s_Box_ZeroRowResult)
        var s_ZeroBoxResult = rowResult_SBoxZero?.get(s_Box_ZeroColumnResult)
        println("s_ZeroBoxResult = $s_ZeroBoxResult")
        var rowReulst_SBoxOne = hashMap?.S1_map?.get(s_BoxOneRowResult)
        var s_OneBoxResult = rowReulst_SBoxOne?.get(s_BoxOneColumnResult)
        println("s_OneBoxResult = $s_OneBoxResult")
        var s_ZeroBoxResultToBinary = Integer.toBinaryString(s_ZeroBoxResult!!)
        var s_OneBoxResultToBinary = Integer.toBinaryString(s_OneBoxResult!!)
        var s_ZeroBoxResultToBinaryInt = Integer.valueOf(s_ZeroBoxResultToBinary)
        var s_OneBoxResultToBinaryInt = Integer.valueOf(s_OneBoxResultToBinary)
        binarySBoxZeroResult = String.format("%02d", s_ZeroBoxResultToBinaryInt)
        binarySBoxOneResult = String.format("%02d", s_OneBoxResultToBinaryInt)
        println("binarySBoxZeroResult = $binarySBoxZeroResult")
        println("binarySBoxOneResult = $binarySBoxOneResult")
        SBoxResultInput()
        leftIPXorFfun()
    }

    fun SBoxResultInput() { //SBox 결과값 넣어주는 함수
        for (i in binarySBoxZeroResult?.indices!!) {
            println("index = $i")
            SBoxResultTemp[i] = binarySBoxZeroResult!![i].toString()
            SBoxResultTemp[SBoxIndex[i]] = binarySBoxOneResult!![i].toString()
        }
        for (i in SBoxResultTemp.indices)
            println("SBoxResultTemp index $i = ${SBoxResultTemp[i]}")

        for (i in SBoxResult.indices) { //P4 순서대로 다시 정렬
            SBoxResult[i] = SBoxResultTemp[P4[i] - 1]
            println("SBoxResult index $i = ${SBoxResult[i]}")
        }
    }

    fun leftIPXorFfun() {
        for (i in SBoxResult.indices) {
            if (SBoxResult[i] == IP[LEFT[i] - 1])
                fkResultLeft[i] = "0"
            else
                fkResultLeft[i] = "1"
        }
        for (i in fkResultLeft.indices)
            IP[i] = fkResultLeft[i]

        println("Finish fk = ${IP[0]} ${IP[1]} ${IP[2]} ${IP[3]} ${IP[4]} ${IP[5]} ${IP[6]} ${IP[7]}")
    }

    fun switchFun() {
        var tempLeftFour = arrayOf("0", "0", "0", "0") //왼쪽것을 오른쪽으로 옮기기위해서 변수 하나 만들어줌
        var tempRightFour = arrayOf("0", "0", "0", "0")
        for (i in tempLeftFour.indices) {
            tempLeftFour[i] = IP[switch_FromRightToLeft[i] - 1] // 이것하면 왼쪽것 4개가 temp 에 들어감
            tempRightFour[i] = IP[switch_FromLeftToRight[i] - 1]
            IP[switch_FromRightToLeft[i] - 1] = tempRightFour[i]
            IP[switch_FromLeftToRight[i] - 1] = tempLeftFour[i]
        }
        println("switch_fk = ${IP[0]} ${IP[1]} ${IP[2]} ${IP[3]} ${IP[4]} ${IP[5]} ${IP[6]} ${IP[7]}")
    }

    fun security_IP_1() {
        for (i in IP_1_Index.indices) {
            IP_1[i] = IP[IP_1_Index[i] - 1]
        }
        println("IP_1 = ${IP_1[0]} ${IP_1[1]} ${IP_1[2]} ${IP_1[3]} ${IP_1[4]} ${IP_1[5]} ${IP_1[6]} ${IP_1[7]}")

        var str = (IP_1[0] + IP_1[1] + IP_1[2] + IP_1[3] + IP_1[4] + IP_1[5] + IP_1[6] + IP_1[7]).trim()
        println("$str")
        var security_char = Integer.parseInt(str, 2) //첫번째 매개변수 string 두번째 n진수

        security_sentence += (security_char.toChar().toString())

        println("security_sentence = ${security_sentence}")
    }

    fun decryption() {
        for (i in security_sentence?.indices!!) {
            var security_sentence_To_Byte = security_sentence!![i]?.toByte() // 암호문장 앞의것부터 바이트로 바꿈

            security_sentence_To_Byte = if(security_sentence_To_Byte < 0)  //Byte값이 0보다 작으면 +값되도록 표현식으로 하였음
                (-security_sentence_To_Byte).toByte()
            else
                security_sentence_To_Byte

            var security_sentence_toBinaryString = Integer.toBinaryString(security_sentence_To_Byte.toInt()) //바이트 해준것 2진수로 표현
            println("temp = $security_sentence_To_Byte")
            println("binary = $security_sentence_toBinaryString")
            var security_To_Int = Integer.valueOf(security_sentence_toBinaryString) //int 형으로 변환 해줌
            security_sentence_Binary = String.format("%08d", security_To_Int) //8자리가 안되면 8자리로 맞춰서 해줌
            println("security_sentence_Binary = $security_sentence_Binary")
            make_security_IP()
            println("IP index $i = ${IP[0]} ${IP[1]} ${IP[2]} ${IP[3]} ${IP[4]} ${IP[5]} ${IP[6]} ${IP[7]}")
            E_P_make()//처음 fk 했을때 E_P 확장 시켜주기
            println("EP index $i = ${E_P[0]} ${E_P[1]} ${E_P[2]} ${E_P[3]} ${E_P[4]} ${E_P[5]} ${E_P[6]} ${E_P[7]}")
//            exclusiveOR_EP_K1()
//            println("F_Fun $i = ${F_FUN[0]} ${F_FUN[1]} ${F_FUN[2]} ${F_FUN[3]} ${F_FUN[4]} ${F_FUN[5]} ${F_FUN[6]} ${F_FUN[7]}")
//            S_Box_calculator()
//            switchFun()
//            E_P_make()//두번째 K2로 할때 확장
//            exclusiveOR_EP_K2()
//            S_Box_calculator()
//            security_IP_1()
        }
    }
}
