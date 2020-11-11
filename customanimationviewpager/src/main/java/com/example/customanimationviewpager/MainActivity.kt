package com.example.customanimationviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter


        viewPagerInit()
        viewPager.setPageTransformer(true, CustomPageTransformer())
    }

    private fun viewPagerInit() {


    }

    internal class MoviePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
        var items: ArrayList<Fragment> = ArrayList<Fragment>()
        override fun getItem(i: Int): Fragment {   // 아이템 리턴
            return items[i]
        }

        override fun getCount(): Int {
            return items.size
        }

        // 추가
        fun addItem(item: Fragment) {
            items.add(item)
        }
    }
}
