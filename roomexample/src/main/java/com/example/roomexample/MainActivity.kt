package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var studentDB: StudentDB? = null
    private var studentList = listOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDB = StudentDB.getInstance(this)

        getAll()

        button.setOnClickListener {
            search()
            //setText()
        }
    }

    private fun getAll(){
        val r = Runnable {
            studentList = studentDB?.studentDAO()?.getAll()!!

            //setText()

        }

        val thread = Thread(r)
        thread.start()
    }

    private fun search(){
        var handler = Handler()
        val r = Runnable {
            studentList = studentDB?.studentDAO()?.search( "%${editText.text.toString()}%")!!
            setText(handler)
            //findViewById<TextView>(R.id.textView).text = str
        }

        val thread = Thread(r)
        thread.start()

    }

    private fun setText(handler: Handler){

        val r = Runnable {
            var str = ""
            for(i in 0 until studentList.size){
                str += " ${studentList[i].name} ${studentList[i].age} ${studentList[i].gender} \n"
            }
            findViewById<TextView>(R.id.textView).text = str
        }


        handler.post(r)
    }
}