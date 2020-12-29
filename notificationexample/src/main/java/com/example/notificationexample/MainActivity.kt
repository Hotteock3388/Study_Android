package com.example.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button).setOnClickListener {
            createNotification()
        }

    }

    private fun createNotification() {


        val builder = NotificationCompat.Builder(this, "default")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("알람 세부 텍스트")
        builder.color = Color.RED
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true)

        
        // 알림 표시
        val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())
    }



    //region Notification 채널 생성 + 등록


    //endregion
}