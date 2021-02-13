package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var myReceiver : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myReceiver = MyReceiver()

    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        //로컬로 등록
        //로컬로 등록
        filter.addAction(MyReceiver().myAction)
        registerReceiver(myReceiver, filter)

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(myReceiver)
    }

    fun sendMyBroadCast(view: View){
        val intent = Intent(MyReceiver().myAction)
        sendBroadcast(intent)
    }


}