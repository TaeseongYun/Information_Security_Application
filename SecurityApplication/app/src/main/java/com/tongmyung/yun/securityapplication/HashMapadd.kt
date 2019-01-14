package com.tongmyung.yun.securityapplication

class HashMapadd{

    var S0_map = HashMap<Int, ArrayList<Int>>()
    var S1_map = HashMap<Int, ArrayList<Int>>()
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

        fun S0_map_init () {
            S0_map.put(0,first_S0)
            S0_map.put(1,second_S0)
            S0_map.put(2, third_S0)
            S0_map.put(3, firth_S0)
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

        fun S1_map_init() {
            S1_map.put(0,first_S1)
            S1_map.put(1,second_S1)
            S1_map.put(2, third_S1)
            S1_map.put(3, firth_S1)
        }

        first_S1_init()
        second_S1_init()
        third_S1_init()
        firth_S1_init()
        S1_map_init()
    }
}