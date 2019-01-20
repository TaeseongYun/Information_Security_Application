package com.tongmyung.yun.securityapplication.model

class HashMapadd{

    var S0_map = HashMap<Int, List<Int>>()
    var S1_map = HashMap<Int, List<Int>>()
    val first_list_S0 = arrayOf(1,0,3,2)
    val second_list_S0 = arrayOf(3,2,1,0)
    val third_list_S0 = arrayOf(0,2,1,3)
    val firth_list_S0 = arrayOf(3,1,3,2)

    val first_list_S1 = arrayOf(0,1,2,3)
    val second_list_S1 = arrayOf(2,0,1,3)
    val third_list_S1 = arrayOf(3,0,1,0)
    val firth_list_S1 = arrayOf(2,1,0,3)

    init {
        S0_map_add()
        S1_map_add()
    }
    fun S0_map_add(){
        var first_S0 = ArrayList<Int>()
        var second_S0 = ArrayList<Int>()
        var third_S0 = ArrayList<Int>()
        var firth_S0 = ArrayList<Int>()

        val s0_mapInput = listOf(first_S0, second_S0, third_S0, firth_S0)

        fun first_S0_init() {
            for(i in 0 until 4) {
                first_S0.add(first_list_S0[i])
            }
        }

        fun second_S0_init() {
            for(i in 0 until 4) {
                first_S0.add(second_list_S0[i])
            }
        }

        fun third_S0_init() {
            for(i in 0 until 4) {
                first_S0.add(third_list_S0[i])
            }
        }

        fun firth_S0_init() {
            for(i in 0 until 4) {
                first_S0.add(firth_list_S0[i])
            }
        }

        //with 라는 람다수식지정함수 를 사용하여 S0_map 을 지정해주고 객체 사용 빈도수를 확 줄임
        fun S0_map_init () = with(S0_map) {
            for(index in 0 until 4) {
                this[index] = s0_mapInput[index]
            }
        }

        first_S0_init()
        second_S0_init()
        third_S0_init()
        firth_S0_init()
        S0_map_init()
    }
    fun S1_map_add(){
        var first_S1 = ArrayList<Int>()
        var second_S1 = ArrayList<Int>()
        var third_S1 = ArrayList<Int>()
        var firth_S1 = ArrayList<Int>()

        val s1_mapInput = listOf(first_S1, second_S1, third_S1, firth_S1)

        fun first_S1_init() {
            for(i in 0 until 4) {
                first_S1.add(first_list_S1[i])
            }
        }

        fun second_S1_init() {
            for(i in 0 until 4) {
                second_S1.add(second_list_S1[i])
            }
        }

        fun third_S1_init() {
            for(i in 0 until 4) {
                third_S1.add(third_list_S1[i])
            }
        }

        fun firth_S1_init() {
            for(i in 0 until 4) {
                firth_S1.add(firth_list_S1[i])
            }
        }

        //with 라는 람다수식지정함수 를 사용하여 S1_map 을 지정해주고 객체 사용 빈도수를 확 줄임
        fun S1_map_init() = with(S1_map) {
            for(index in 0 until 4) { //0..3
                this[index] = s1_mapInput[index]
            }
        }

        first_S1_init()
        second_S1_init()
        third_S1_init()
        firth_S1_init()
        S1_map_init()
    }
}