package com.eim.kotlin.workout.planner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eim.kotlin.workout.planner.databinding.ActivityTimerBinding
import com.eim.kotlin.workout.planner.ui.progress.timer.TimerDataHelper
import java.util.*

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    lateinit var timerDataHelper: TimerDataHelper

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timerDataHelper = TimerDataHelper(applicationContext)

        binding.btnStartTimer.setOnClickListener{ startStopAction() }
        binding.btnResetTimer.setOnClickListener{ resetAction() }

        if (timerDataHelper.timerCounting()) {
            startTimer()
        } else {
            stopTimer()
            if (timerDataHelper.startTime() != null && timerDataHelper.stopTime() != null) {
                val time = Date().time - calcRestartTime().time
                binding.tvTimer.text = timeStringFromLong(time)
            }
        }

        timer.scheduleAtFixedRate(TimeTask(), 0, 500)
    }

    private inner class TimeTask: TimerTask() {
        override fun run() {
            if (timerDataHelper.timerCounting()) {
                val time = Date().time - timerDataHelper.startTime()!!.time
                binding.tvTimer.text = timeStringFromLong(time)
            }
        }
    }

    private fun resetAction() {
        timerDataHelper.setStopTime(null)
        timerDataHelper.setStartTime(null)
        stopTimer()
        binding.tvTimer.text = timeStringFromLong(0)
    }

    private fun stopTimer() {
        timerDataHelper.setTimerCounting(false)
        binding.btnStartTimer.text = getString(R.string.start)
    }

    private fun startTimer() {
        timerDataHelper.setTimerCounting(true)
        binding.btnStartTimer.text = getString(R.string.stop)
    }

    private fun startStopAction() {
        if (timerDataHelper.timerCounting()) {
            timerDataHelper.setStopTime(Date())
            stopTimer()
        } else {
            if (timerDataHelper.stopTime() != null) {
                timerDataHelper.setStartTime(calcRestartTime())
                timerDataHelper.setStopTime(null)
            } else {
                timerDataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun calcRestartTime(): Date {
        val diff = timerDataHelper.startTime()!!.time - timerDataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    private fun timeStringFromLong(ms: Long): String {
        val seconds = (ms / 1000) % 60
        val minutes = ((ms / 1000 * 60) % 60)
        val hours = ((ms / 1000 * 60 * 60) % 24)

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}