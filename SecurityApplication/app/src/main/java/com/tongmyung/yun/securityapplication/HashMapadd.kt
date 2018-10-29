package com.tongmyung.yun.securityapplication

class HashMapadd{

    var S0_map = HashMap<Int, ArrayList<Int>>()
    var S1_map = HashMap<Int, ArrayList<Int>>()

    init {
        S0_map_add()
        S1_map_add()
    }
    fun S0_map_add(){
        var first_S0 = ArrayList<Int>()
        first_S0.add(1)
        first_S0.add(0)
        first_S0.add(3)
        first_S0.add(2)
        var second_S0 = ArrayList<Int>()
        second_S0.add(3)
        second_S0.add(2)
        second_S0.add(1)
        second_S0.add(0)
        var third_S0 = ArrayList<Int>()
        third_S0.add(0)
        third_S0.add(2)
        third_S0.add(1)
        third_S0.add(3)
        var firth_S0 = ArrayList<Int>()
        firth_S0.add(3)
        firth_S0.add(1)
        firth_S0.add(3)
        firth_S0.add(2)
        S0_map.put(0,first_S0)
        S0_map.put(1,second_S0)
        S0_map.put(2, third_S0)
        S0_map.put(3, firth_S0)
    }
    fun S1_map_add(){
        var first_S1 = ArrayList<Int>()
        first_S1.add(0)
        first_S1.add(1)
        first_S1.add(2)
        first_S1.add(3)
        var second_S1 = ArrayList<Int>()
        second_S1.add(2)
        second_S1.add(0)
        second_S1.add(1)
        second_S1.add(3)
        var third_S1 = ArrayList<Int>()
        third_S1.add(3)
        third_S1.add(0)
        third_S1.add(1)
        third_S1.add(0)
        var firth_S1 = ArrayList<Int>()
        firth_S1.add(2)
        firth_S1.add(1)
        firth_S1.add(0)
        firth_S1.add(3)
        S1_map.put(0,first_S1)
        S1_map.put(1,second_S1)
        S1_map.put(2, third_S1)
        S1_map.put(3, firth_S1)
    }
}