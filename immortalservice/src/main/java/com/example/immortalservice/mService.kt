package com.example.immortalservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import java.lang.Thread.sleep

class mService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        repeatLog("onCreate")
        startForegroundService()
    }

    private fun repeatLog(location: String) {
        Log.d("TestLog", location)
        for(i in 0 until 10000){
            Log.d("TestLog", "$location $i")
            sleep(1000)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        repeatLog("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    fun startForegroundService(){
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val remoteViews = RemoteViews(packageName, R.layout.notification_service)

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "snwodeer_service_channel"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "SnowDeer Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, CHANNEL_ID)

        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
            .setContent(remoteViews)
            .setContentIntent(pendingIntent)

        startForeground(1, builder.build())
        repeatLog("startForegroundService")
    }
}