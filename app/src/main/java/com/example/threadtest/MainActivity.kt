package com.example.threadtest

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import java.text.SimpleDateFormat
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var startButton: Button
    lateinit var pauseButton: Button
    lateinit var recordButton: Button
    lateinit var timerTextView: TextView
    lateinit var recordTextView: TextView
    var status = PAUSE
    var lastTime: Long = 0L
    val countHandler = CountHandler()

    companion object { // static 변수, 상수 등을 선언
        const val PAUSE = 1
        const val START = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.btn_start)
        pauseButton = findViewById(R.id.btn_pause)
        recordButton = findViewById(R.id.btn_record)
        timerTextView = findViewById(R.id.tv_timer)
        recordTextView = findViewById(R.id.tv_record)

        recordButton.setOnClickListener {
            val s = "${recordTextView.text}${timerTextView.text}\n"
            recordTextView.text = s
        }

        startButton.setOnClickListener(this)
        pauseButton.setOnClickListener(this)
        //Thread(CountRunnable()).start()
    }

//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btn_start -> {
//                status = START
//                //Thread(CountRunnable()).start()
//            }
//            R.id.btn_pause -> {
//                status = PAUSE
//            }
//        }
//    }
//
//        inner class CountRunnable : Runnable{
//            override fun run() {
//                var dateFormat = SimpleDateFormat("mm:ss:SSS")
//                while (status== START){
//                    lastTime +=1L
//                    Thread.sleep(100)
//                    timerTextView.text =  dateFormat.format(lastTime).toString()
//                }
//            }
//        }

    //------------------------------------------------------------------------
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pause -> {
                status = PAUSE // TODO
                countHandler.removeMessages(START)
            }
            R.id.btn_start -> {
                status = START
                countHandler.removeMessages(START)
                countHandler.sendEmptyMessage(START)
            }
        }
    }

    inner class CountHandler : Handler() {
        //        val dateFormat = SimpleDateFormat("mm:ss:SSS")
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                START -> {
                    ++lastTime
                    val milli = lastTime % 1000
                    val second = lastTime / 1000 % 60
                    val minute = lastTime / 1000 / 60
                    timerTextView.text = "${String.format("%02d", minute)}:${String.format(
                        "%02d",
                        second
                    )}:${String.format("%03d", milli)}"
                    sendEmptyMessageDelayed(msg.what, 1L)

                }
            }
        }
    }


}







