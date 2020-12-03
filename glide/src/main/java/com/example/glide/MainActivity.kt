package com.example.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imageView = findViewById<ImageView>(R.id.imageView)

        Glide.with(this)
            .load(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}