package com.example.foregroundservice

import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.lang.Thread.sleep


class ScreenService: Service() {

    private lateinit var mReceiver: ScreenReceiver
    private val CHANNEL_ID = "testNoti"
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mReceiver = ScreenReceiver()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(mReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        repeatLog()
        if (intent != null) {
            if (intent.action == null) {
                if (mReceiver == null) {
                    mReceiver = ScreenReceiver()
                    val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
                    registerReceiver(mReceiver, filter)
                }
            }
        }
        startForegroundService()

        return START_STICKY
    }

    private fun repeatLog() {
        val t = Thread(Runnable {
            for (i in 0 until 100000){
                sleep(500)
                Log.d("TestLog", "$i")
            }
        })
        t.start()
    }

    private fun startForegroundService(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("포그라운드 서비스 테스트")
        builder.setContentText("포그라운드 서비스 테스트 중")

        val notiIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notiIntent, 0)
        builder.setContentIntent(pendingIntent)
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(NotificationChannel(CHANNEL_ID, "기본 채널", NotificationManager.IMPORTANCE_DEFAULT))
        }
        val noti = builder.build()

        startForeground(142, noti)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
        }
    }

}