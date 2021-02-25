package com.example.immortalservice

import android.app.*
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

class ClipboardService : Service(), ClipboardManager.OnPrimaryClipChangedListener {

    private lateinit var mManager: ClipboardManager

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        mManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        mManager.addPrimaryClipChangedListener(this)
    }

    private val TAG = "ClipboardService"

    override fun onPrimaryClipChanged() {
        if (mManager.primaryClip != null) {
            val data: ClipData = mManager.primaryClip!!

            val dataCount = data.itemCount
            for (i in 0 until dataCount) {
                val item = data.getItemAt(i).coerceToText(this)

                Log.d(TAG, "clip data = item $item")
                toast("[Clipboard] $item")
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        mManager.removePrimaryClipChangedListener(this)
        // 서비스 종료 시 1초 뒤 알람이 실행되도록 호출
        setAlarmTimer()
    }

    private fun setAlarmTimer() {
        val c: Calendar = Calendar.getInstance()
        c.timeInMillis = System.currentTimeMillis()
        c.add(Calendar.SECOND, 1)
        val intent = Intent(this, AlarmReceiver::class.java)
        val sender = PendingIntent.getBroadcast(this, 0, intent, 0)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, c.timeInMillis, sender)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        toast("Start Service")
        Log.d("TestLog", "onStartcommand" )
        return START_STICKY
    }
}