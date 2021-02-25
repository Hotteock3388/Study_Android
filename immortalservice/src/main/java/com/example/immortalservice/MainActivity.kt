package com.example.immortalservice

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var mIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = ClipboardService::class.java
        mIntent = Intent(applicationContext, serviceClass)
        if (!this.isServiceRunning(serviceClass)) {
            Log.e(TAG, "Service is not running - START SERVICE")
            // App 실행 시 서비스(ClipboardService) 시작
            // App 실행 시 foreground 이므로 startService 로 호출
            startService(mIntent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // App을 종료할 때 서비스(ClipboardService)를 종료
        stopService(mIntent)
    }
}

fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
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