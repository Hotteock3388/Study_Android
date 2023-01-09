package com.nflux.windowblurs

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.Window
import androidx.appcompat.content.res.AppCompatResources.getDrawable

class BlurDrawable(private val context: Context, private val window: Window) : Drawable() {
    private lateinit var bgView: View
    private lateinit var view: View

    private val size = 6
    private val squal = size * size
    private val pixels = IntArray(squal)
    lateinit var viewBitmap: Bitmap

    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width, view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun test(context:Context): Bitmap {
        val drawable = getDrawable(context, R.drawable.img_test)
        val bitmapDrawable = drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap

        return bitmap
    }

    fun getBitmapFromView2(view: View, callback: (Bitmap) -> Unit) {

        window.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(window, Rect(locationOfViewInWindow[0], locationOfViewInWindow[1], locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height), bitmap, { copyResult ->
                    if (copyResult == PixelCopy.SUCCESS) {
                        callback(bitmap)
                    }
                    // possible to handle other result codes ...
                }, Handler())
            } catch (e: IllegalArgumentException) {
                // PixelCopy may throw IllegalArgumentException, make sure to handle it
                e.printStackTrace()
            }
        }
    }

    override fun draw(canvas: Canvas) {
        bgView.isDrawingCacheEnabled = true

//        val bm: Bitmap = bgView.drawingCache ?: return
//        val bm: Bitmap = bgView.getDrawingCache(false)?:return
        getBitmapFromView2(bgView) {
            var bm: Bitmap = it
            Log.d("TestLog", "it = $it")

            bm = test(context)


//        val bm: Bitmap = getBitmapFromView(bgView)
            try {
                if (viewBitmap == null) {
                    viewBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
                }
            }catch (e: UninitializedPropertyAccessException){
                viewBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            }

            val pRect = Rect()
            val cRect = Rect()

            bgView.getGlobalVisibleRect(pRect)
            view.getGlobalVisibleRect(cRect)

            var sumR = 0
            var sumG = 0
            var sumB = 0
            var red = 0
            var green = 0
            var blue = 0

            for (i in cRect.left - pRect.left until cRect.right - pRect.left) {

                for (j in cRect.top - pRect.top until cRect.bottom - pRect.top) {

                    bm.getPixels(pixels, 0, size, i - size / 2, j - size / 2, size, size)

                    for (k in 0 until squal) {
                        sumR += pixels[k] shr 16 and 0xFF
                        sumG += pixels[k] shr 8 and 0xFF
                        sumB += pixels[k] and 0xFF
                    }

                    red = sumR / squal
                    green = sumG / squal
                    blue = sumB / squal

                    val pixel = -0x1000000 + (red shl 16) + (green shl 8) + blue

                    viewBitmap!!.setPixel(
                        i - (cRect.left - pRect.left),
                        j - (cRect.top - pRect.top),
                        pixel
                    )

                    sumB = 0
                    sumG = sumB
                    sumR = sumG
                    blue = 0
                    green = blue
                    red = green
                }
            }
            canvas.drawBitmap(viewBitmap, null, bounds, null)

        }
    }

    fun getBitmapView(): Bitmap? {
        bgView.isDrawingCacheEnabled = true

        val bm: Bitmap = bgView.drawingCache ?: return null
//        val bm: Bitmap = bgView.getDrawingCache(false)?:return

        if (viewBitmap == null) {
            viewBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        }

        val pRect = Rect()
        val cRect = Rect()

        bgView.getGlobalVisibleRect(pRect)
        view.getGlobalVisibleRect(cRect)

        var sumR = 0
        var sumG = 0
        var sumB = 0
        var red = 0
        var green = 0
        var blue = 0

        for (i in cRect.left - pRect.left until cRect.right - pRect.left) {

            for (j in cRect.top - pRect.top until cRect.bottom - pRect.top) {

                bm.getPixels(pixels, 0, size, i - size / 2, j - size / 2, size, size)

                for (k in 0 until squal) {
                    sumR += pixels[k] shr 16 and 0xFF
                    sumG += pixels[k] shr 8 and 0xFF
                    sumB += pixels[k] and 0xFF
                }

                red = sumR / squal
                green = sumG / squal
                blue = sumB / squal

                val pixel = -0x1000000 + (red shl 16) + (green shl 8) + blue

                viewBitmap!!.setPixel(
                    i - (cRect.left - pRect.left),
                    j - (cRect.top - pRect.top),
                    pixel
                )

                sumB = 0
                sumG = sumB
                sumR = sumG
                blue = 0
                green = blue
                red = green
            }
        }
        return viewBitmap
    }

    fun getView(): View { return view }
    fun setView(view: View) { this.view = view }

    fun getParentView(): View { return bgView }
    fun setBGView(parentView: View) { bgView = parentView }

    override fun getOpacity(): Int { return PixelFormat.UNKNOWN }
    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(cf: ColorFilter?) {}
}
