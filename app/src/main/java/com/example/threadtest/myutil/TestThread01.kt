package com.example.threadtest.myutil

import android.util.Log

class TestThread01 : Runnable {
    override fun run() {
        //1초 단위로 log를 출력해보장!
        for (i in 1..10) {
            Thread.sleep(1000)
            Log.d("My", "$i 초")
        }
    }
}