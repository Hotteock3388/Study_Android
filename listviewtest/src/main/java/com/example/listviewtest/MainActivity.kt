package com.example.listviewtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = "${baseContext?.applicationInfo?.dataDir}/files/data.txt"
        var arrayList = ArrayList<String>()

        try {
            val br = BufferedReader(InputStreamReader(FileInputStream(file), "x-windows-949"))


            BufferedReader(InputStreamReader(FileInputStream(file), "x-windows-949")).use {
                var line: String?
                while (it.readLine().also { line = it } != null){
                    Log.d("TestLog", "readLine = ${line.toString()}")
                    if (line?.contains("존재")!!){
                        arrayList.add(line!!)
                    }
                }
            }

        }catch (e: IOException){
            e.printStackTrace()
        }

        Log.d("TestLog", "size = ${arrayList.size}")


    }

}