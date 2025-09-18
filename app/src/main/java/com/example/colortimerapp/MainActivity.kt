package com.example.colortimerapp

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val colors by lazy {
        listOf(
            R.color.red,
            R.color.green,
            R.color.blue,
            R.color.yellow,
            R.color.purple_200,
            R.color.teal_200
        )
    }

    private var index = 0
    private val handler = Handler(Looper.getMainLooper())
    private val changeRunnable = object : Runnable {
        override fun run() {
            val colorRes = colors[index % colors.size]
            val color = ContextCompat.getColor(this@MainActivity, colorRes)
            window.decorView.setBackgroundColor(color)
            index++
            handler.postDelayed(this, 10_000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        handler.post(changeRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(changeRunnable)
    }
}
