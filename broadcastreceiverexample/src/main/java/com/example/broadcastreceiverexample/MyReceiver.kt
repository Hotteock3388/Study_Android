package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    val myAction = "com.example.broadcastreceiverexample.ACTION_MY_BROADCAST"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent?.action)) {
            Toast.makeText(context, "전원 연결", Toast.LENGTH_SHORT).show()
        }
        else if(myAction.equals(intent?.action)) {
            Toast.makeText(context, "방송", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "ACTION = ${intent?.action}", Toast.LENGTH_SHORT).show()
        }

    }

}