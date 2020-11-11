package com.example.customanimationviewpager

import android.view.View
import androidx.core.graphics.rotationMatrix
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class CustomPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {


        page.apply {
            //val sizeX = (0.9 * scaleX).toFloat()
            //val sizeY = (0.9 * scaleY).toFloat()
            scaleX = 0.8F
            scaleY = 0.8F
            rotation = position * 40F
            translationY = abs(position) * 400

        }
    }
}