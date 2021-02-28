package com.example.foregroundservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class CustomReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TestLog", "${intent.action}")

        if(intent.action.equals(Intent.ACTION_SCREEN_OFF)){
            Log.d("TestLog", "ACTION_SCREEN_OFF")
            startService(context)
        }

        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("TestLog", "boot complete received")
            startService(context)
        }
    }

    private fun startService(context: Context){
        val i = Intent(context, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }

}
