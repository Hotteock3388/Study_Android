package com.example.immortalservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {

    private val TAG: String = "BootCompletedReceiver"

    override fun onReceive(context: Context, intent: Intent) {

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val action = intent.action

        Log.d(TAG, "Receive ACTION $action")
        if (action == null) {
            Log.d(TAG, "action is null")
            return
        }

        if (TextUtils.equals(action, Intent.ACTION_BOOT_COMPLETED)) {
            Log.d(TAG, "boot complete received")

            val serviceClass = ClipboardService::class.java
            val intent = Intent(context, serviceClass)
            if (!context.isServiceRunning(serviceClass)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d(TAG, "Service is not running - START FOREGROUND SERVICE")
                    context.startForegroundService(intent)
                } else {
                    Log.d(TAG, "Service is not running - START SERVICE")
                    context.startService(intent)
                }
            }
        }
    }
}
