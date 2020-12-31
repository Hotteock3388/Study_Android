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
    lateinit var notificationManager : NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNotificationManager()


        findViewById<Button>(R.id.button).setOnClickListener {
            createBigTextNotification()
        }

    }

    private fun initNotificationManager() {

        notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        setNotificationChannel()
    }

    private fun setNotificationChannel() {
        //val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                //id는 말 그대로 Notification의 ID값
                NotificationChannel("Study_Android App", "Notification Test App", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
    }

    private fun createNotification() {
        val builder = NotificationCompat.Builder(this, "default")

        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 3

        // Notification의 왼쪽 위에 뜨는 작은 아이콘
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)

        // 아이콘 오른쪽, 맨 상단에 뜨는 Notification의 제목
        builder.setContentTitle("알림 제목")
        // Notification의 내용
        builder.setContentText("Notification Test 입니다.")

        //Small Icon의 색깔
        builder.color = Color.BLUE
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(false)
        builder.setContentIntent(pendingIntent)


        // id값은 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())
    }


    private fun createBigTextNotification() {

        val builder = NotificationCompat.Builder(this, "default")

        val style = NotificationCompat.BigTextStyle()

        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 3


        style.bigText("알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트")


        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        builder.setContentIntent(pendingIntent)   // 10

        builder.setStyle(style)

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())
    }



    //region Notification 채널 생성 + 등록


    //endregion
}