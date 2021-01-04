package com.example.swipelayoutexample

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<LinearLayout>(R.id.bottom_wrapper).setOnClickListener {
            Toast.makeText(applicationContext, "Delete!", Toast.LENGTH_SHORT).show()
        }

        var swipeLayout =  findViewById<SwipeLayout>(R.id.swipeLayout)

        swipeLayout.addSwipeListener(object : SwipeListener {

            override fun onClose(layout: SwipeLayout) {
                //when the SurfaceView totally cover the BottomView.
                Toast.makeText(applicationContext, "Close!", Toast.LENGTH_SHORT).show()
            }

            override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                Toast.makeText(applicationContext, "Update!", Toast.LENGTH_SHORT).show()
                //you are swiping.
            }

            override fun onStartOpen(layout: SwipeLayout) {
                Toast.makeText(applicationContext, "StartOpen!", Toast.LENGTH_SHORT).show()
            }

            override fun onOpen(layout: SwipeLayout) {
                //when the BottomView totally show.
                Toast.makeText(applicationContext, "Open!", Toast.LENGTH_SHORT).show()
            }

            override fun onStartClose(layout: SwipeLayout) {
                Toast.makeText(applicationContext, "StartClose!", Toast.LENGTH_SHORT).show()
            }

            override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                //Toast.makeText(applicationContext, "OnHandRelease!", Toast.LENGTH_SHORT).show()
            //when user's hand released.
            }
        })

    }
}