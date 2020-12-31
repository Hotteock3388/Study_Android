package com.example.notificationexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat


class MainActivity : AppCompatActivity() {

    var i = 0
    var NOTIFICATIONID = 1000
    lateinit var notificationManager : NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cNum = 0

        initNotificationManager()


        findViewById<Button>(R.id.button).setOnClickListener {
            messagingStyleNotification(++cNum)
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

    //region Default Notification
    private fun DefaultNotification() {
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
    private fun BigTextNotification() {

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
    fun PendingNotification(){

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

    fun BigPictureNotification(){

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
    fun messagingStyleNotification(clickedNum : Int){
        var messageList = ArrayList<String>()

        messageList.add("안녕?"); messageList.add("난 지금 너무 졸려..."); messageList.add("넌 지금 뭐해?"); messageList.add("달고 짠 거 먹고싶다"); messageList.add("그냥 그렇다고"); messageList.add("멍청아")


        val builder = NotificationCompat.Builder(this, "default")

        val userIcon1 = IconCompat.createWithResource(this, R.drawable.black_cat)
        val userName1 = "Ho"
        val timestamp = System.currentTimeMillis()

        val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()
        val style = NotificationCompat.MessagingStyle(user1)

        for( j in 0 until clickedNum ){
            if(j < messageList.size) {
                style.addMessage(messageList[j], timestamp, user1)
            }

        }

        //style.addMessage("안녕?", timestamp, user1)
        //style.addMessage("졸려,,,", timestamp, user1)


        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")
        builder.setStyle(style)



        notificationManager.notify(NOTIFICATIONID, builder.build())
    }

    //region Notification 채널 생성 + 등록

    fun basecode(){

        val builder = NotificationCompat.Builder(this, "default")

        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon)
        builder.setContentTitle("알림 제목")
        builder.setContentText("테스트")

        notificationManager.notify(i++, builder.build())


    }

    //endregion
}