package com.example.chickeninviders.game.gameplay

// TimerHandler.kt
import android.os.Handler
import android.os.Looper

class TimerHandler(
    private val updateInterval: Long = 1L,
    private val onTick: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    private val timerRunnable = object : Runnable {
        override fun run() {
            onTick()
            if (isRunning) {
                handler.postDelayed(this, updateInterval)
            }
        }
    }

    fun start() {
        if (!isRunning) {
            isRunning = true
            handler.post(timerRunnable)
        }
    }

    fun stop() {
        isRunning = false
        handler.removeCallbacks(timerRunnable)
    }
}
