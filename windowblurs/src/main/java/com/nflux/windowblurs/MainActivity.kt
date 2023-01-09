package com.nflux.windowblurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_BLUR_BEHIND
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier
import java.nio.file.WatchEvent

class MainActivity : AppCompatActivity() {

    var blurBehindRadius: Int = 50
    var backgroundBlurRadius: Int = 50


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<LinearLayout>(R.id.linearLayout)

        val button = findViewById<ImageView>(R.id.textViewButton)
//        window.setFlags(FLAG_BLUR_BEHIND, FLAG_BLUR_BEHIND)
//
//        window.setBackgroundBlurRadius(20)
//        window.attributes.blurBehindRadius = blurBehindRadius

//        WindowManager.LayoutParams.setBlurBehindRadius(int)
        button.setOnClickListener {

            CoroutineScope(Main).launch {
                val root = window.decorView.findViewById<View>(android.R.id.content)

                val bd = BlurDrawable(baseContext, window)

                bd.setBGView(layout)
                bd.setView(button)

                button.setBackgroundDrawable(bd)

            }

        }

        window?.windowManager?.addCrossWindowBlurEnabledListener { enabled ->
            if (enabled) {
                setBlurredBackground()

                Toast.makeText(
                    this,
                    "setBlurredBackground()",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Cross-window blur is disabled at runtime_activity",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setBlurredBackground() {
        window.addFlags(
            FLAG_BLUR_BEHIND
        )
        if (windowManager.isCrossWindowBlurEnabled) {
            window.attributes.blurBehindRadius = 50
            window.setBackgroundBlurRadius(backgroundBlurRadius)

            window.attributes.blurBehindRadius = blurBehindRadius
            window.attributes = window.attributes

            BlurredDialog(this, blurBehindRadius, backgroundBlurRadius).show()

        }else{
            Toast.makeText(
                this,
                "Cross-window blur is disabled at runtime_activity",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}