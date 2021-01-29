package com.example.roomexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    private var studentDB: StudentDB? = null
    private var studentList = listOf<Student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        studentDB = StudentDB.getInstance(applicationContext)

        setDummyData()


    }

    private fun setDummyData() {

        val r = Runnable {
            var cnt = 1
            for(i in 0..10){

                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 김효선",19, "남"))
                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 김상현",19, "남"))
                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 유영재",19, "남"))
                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 신이삭",19, "남"))
                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 문상규",19, "남"))
                studentDB?.studentDAO()?.insert(Student(cnt++, "$i 번째 김준우",18, "남"))

            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        val thread = Thread(r)
        thread.start()

    }
}