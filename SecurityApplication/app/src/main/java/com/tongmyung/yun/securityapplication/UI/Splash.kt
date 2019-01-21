package com.tongmyung.yun.securityapplication.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tongmyung.yun.securityapplication.view.MainActivity

class Splash : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            Thread.sleep(1500)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}