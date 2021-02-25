package com.example.immortalservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.Service.START_NOT_STICKY
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ServiceCompat.stopForeground

class RestartService : Service() {

    val NOTIFICATION_ID = 123

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_MIN)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        toast("Start Restart Service")

        val channelId =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                createNotificationChannel("clipboard", "ClipboardService")
            } else {
                ""
            }

        val builder: Notification.Builder =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Notification.Builder(this, channelId)
            } else {
                Notification.Builder(this)
            }

        builder.setContentTitle("ClipboardService Title")
        builder.setContentText("ClipboardService Text")
        builder.setSmallIcon(R.mipmap.ic_launcher)

        val foregroundNoti = builder.build()

        // RestartService를 startForeground로 실행

        startForeground(NOTIFICATION_ID, foregroundNoti)

        // 실제 서비스는 startService로 실행
        startService(Intent(this, ClipboardService::class.java))

        // RestartService를 종료
        stopForeground(true)
        stopSelf()

        return START_NOT_STICKY
    }
}
