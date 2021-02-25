package com.example.lockscreen

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log


class ScreenReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action.equals(Intent.ACTION_SCREEN_OFF)){
            val i = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }
}
