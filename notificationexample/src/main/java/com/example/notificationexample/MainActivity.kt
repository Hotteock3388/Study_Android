package com.example.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat


class MainActivity : AppCompatActivity() {

    var i = 0
    lateinit var notificationManager : NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNotificationManager()


        findViewById<Button>(R.id.button).setOnClickListener {
            headUpNotification()
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
                NotificationChannel("default", "Notification Test App", NotificationManager.IMPORTANCE_DEFAULT)
            )
            Log.d("TestLog", "create Channel")
        }else{
            Log.d("TestLog", "SDK VERSION FAIL")
        }
    }

    //region Default Notification
    private fun defaultNotification() {
        // builder를 이용해서 Notification을 띄운다.(Context, channelID)
        val builder = NotificationCompat.Builder(this, "default")

        // Notification의 왼쪽 위에 뜨는 작은 아이콘
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)

        // 아이콘 오른쪽, 맨 상단에 뜨는 Notification의 제목
        builder.setContentTitle("알림 제목")

        // Notification의 내용
        builder.setContentText("라인 $i")

        // id값은 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())
    }
    //endregion

    //region Big Text Notification
    private fun bigTextNotification() {

        val builder = NotificationCompat.Builder(this, "default")

        val style = NotificationCompat.BigTextStyle()

        style.bigText("알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트알람 세부 텍스트")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        //builder 에 스타일적용
        builder.setStyle(style)


        notificationManager.notify(i++, builder.build())
    }
    //endregion

    //region Pending Intent Notification
    fun pendingNotification(){

        val builder = NotificationCompat.Builder(this, "default")

        var intent = Intent(this, StartActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)


        // builder를 이용해서 Notification을 띄운다.(Context, channelID)

        // Notification의 왼쪽 위에 뜨는 작은 아이콘
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)

        // 아이콘 오른쪽, 맨 상단에 뜨는 Notification의 제목
        builder.setContentTitle("알림 제목")

        // Notification의 내용
        builder.setContentText("테스트")

        /// Notification에 pendingIntent 등록
        builder.setContentIntent(pendingIntent)

        // id값은 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())

    }

    //endregion

    //region Big Picture Notification
    fun bigPictureNotification(){

        val builder = NotificationCompat.Builder(this, "default")

        val style = NotificationCompat.BigPictureStyle()
        style.bigPicture(
            BitmapFactory.decodeResource(resources, R.drawable.black_cat)
        )

        // Notification의 왼쪽 위에 뜨는 작은 아이콘
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)

        // 아이콘 오른쪽, 맨 상단에 뜨는 Notification의 제목
        builder.setContentTitle("알림 제목")

        // Notification의 내용
        builder.setContentText("테스트")

        builder.setStyle(style)   // 3


        // id값은 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(i++, builder.build())
    }
    //endregion

    //region Inbox Style Notification
    fun inBoxStyleNotification(){
        val builder = NotificationCompat.Builder(this, "default")

        val style = NotificationCompat.InboxStyle()

        style.addLine("라인 1");  style.addLine("라인 2")
        style.addLine("라인 3");  style.addLine("라인 4")
        style.addLine("라인 5");  style.addLine("라인 6")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        builder.setStyle(style)

        notificationManager.notify(i++, builder.build())
    }
    //endregion

    //region Messaging Style Notification
    fun messagingStyleNotification(){
        val builder = NotificationCompat.Builder(this, "default")

        val userIcon1 = IconCompat.createWithResource(this, R.drawable.black_cat)
        val userName1 = "Ho"
        val timestamp = System.currentTimeMillis()

        val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()
        val style = NotificationCompat.MessagingStyle(user1)
        style.addMessage("안녕?", timestamp, user1)
        style.addMessage("졸려,,,", timestamp, user1)


        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")
        builder.setStyle(style)


        notificationManager.notify(i++, builder.build())
    }
    //endregion

    //region Head Up Notification
    private fun headUpNotification(){

        val headUpNotificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        headUpNotificationManager.createNotificationChannel(
            //id는 말 그대로 Notification의 ID값
            NotificationChannel("Study_Android App", "Notification Test App2", NotificationManager.IMPORTANCE_HIGH)
        )

        val fullScreenPendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 2


        val builder = NotificationCompat.Builder(this, "default")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        builder.priority = NotificationCompat.PRIORITY_HIGH
        builder.setFullScreenIntent(fullScreenPendingIntent, true)   // 4

        //NotificationManagerCompat.from(this).notify(i++, builder.build())
        headUpNotificationManager.notify(i++, builder.build())

    }
    //endregion

    //region Notification 채널 생성 + 등록
    fun baseCode(){

        val builder = NotificationCompat.Builder(this, "default")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        notificationManager.notify(i++, builder.build())

    }
    //endregion
}