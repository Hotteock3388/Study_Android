package com.example.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cnt = 5
        var temp = 0
        var arrayList = ArrayList<String>()
        for(i in 0..cnt){
            arrayList.add("$i")
        }
//        Log.d("TestLog","For문 끝")
//        Log.d("TestLog","arrayList = $arrayList")
//        Log.d("TestLog","arrayListSize = ${arrayList.size}")

        Singleton.setList(arrayList)

        var myAdapter =  CustomAdapter(applicationContext, arrayList)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()


        //Log.d("TestLog","Adapter 등록 끝")


        button.setOnClickListener {
            Log.d("TestLog", "Click!!!")
            //arrayList.add("${temp++}")
            Singleton.arrayList.add("22")
            myAdapter.notifyDataSetChanged()
        }

    }


}