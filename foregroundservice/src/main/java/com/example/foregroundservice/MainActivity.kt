package com.example.foregroundservice

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.foregroundservice.databinding.ActivityMainBinding
import java.lang.Thread.sleep
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var time = ""
    var date = ""

    val timeFormat = DateTimeFormatter.ofPattern("hh:mm")
    val dateFormat = DateTimeFormatter.ofPattern("MM월 dd일")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        removeTitleAction()
        if(!this.isServiceRunning(ScreenService::class.java)){
            startForegroundService(Intent(applicationContext, ScreenService::class.java))
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)

        val t = Thread(Runnable {
            while(true){
                updateScreen()
                sleep(5000)
            }
        })
        t.start()
    }

    private fun updateScreen() {
        setTime()
        binding.invalidateAll()
    }

    private fun setTime() {
        val localDateTime = LocalDateTime.now()
        time = localDateTime.format(timeFormat)
        date = localDateTime.format(dateFormat) + " ${getDayOfWeek(localDateTime.dayOfWeek)}요일"
    }

    private fun getDayOfWeek(dayOfWeek: DayOfWeek) : String{
        return when(dayOfWeek){
            DayOfWeek.SATURDAY -> "토"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.SUNDAY -> "일"
            else -> "?"
        }
    }

    fun removeTitleAction(){
        // hide actionBar
        val actionBar = supportActionBar
        actionBar?.hide()

        //hide titleBar(fullScreen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.d("isServiceRunning", "Service is running")
                return true
            }
        }
        return false
    }

}