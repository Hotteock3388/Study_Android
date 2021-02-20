package com.example.freewrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val arr = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeArr()

        initRecyclerView()


    }

    private fun initRecyclerView() {
        val adapter = RecyclerViewAdpater(arr)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.notifyDataSetChanged()
    }

    private fun makeArr() {
        for(i in 1 .. 10) {
            arr.add("2101 김상현")
        }

    }
}