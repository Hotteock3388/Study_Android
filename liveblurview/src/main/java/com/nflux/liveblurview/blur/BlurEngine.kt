package com.nflux.liveblurview.blur

import android.graphics.Bitmap

interface BlurEngine {
    fun blur(image: Bitmap, radius: Int): Bitmap
    fun getType(): BlurType
}
