package com.example.customanimationviewpager

import com.example.customanimationviewpager.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class ViewPagerAdapter(private val context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null

    private val     textArray = arrayOf(
        "1번 페이지",
        "2번 페이지",
        "3번 페이지"
    )
    val colorArray = arrayOf(
        "#FFFF5722",
        "#FF00BCD4",
        "#FF9C27B0"
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return textArray.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.page, null)
        val textView = v.findViewById<View>(R.id.titleTextView) as TextView
        val mainLayout = v.findViewById<ConstraintLayout>(R.id.mainLayout)

        mainLayout.setBackgroundColor(Color.parseColor(colorArray[position]))
        textView.text = textArray[position]
        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }

}