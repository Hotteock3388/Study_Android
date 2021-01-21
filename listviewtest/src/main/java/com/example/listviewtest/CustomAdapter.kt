package com.example.listviewtest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_test.view.*

class CustomAdapter (context: Context, dataList: ArrayList<String>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    //var dataList = dataList
    //var position : Int = 0
    //아이템의 갯수
    override fun getItemCount(): Int {
        Log.d("TestLog", "dataList.size = ${Singleton.arrayList.size}")
        return Singleton.arrayList.size
    }

    fun addItem(data : String){
        Singleton.arrayList.add(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_test, parent, false)
        //Log.d("TestLog", "onCreateViewHolder")
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, itemPosition: Int) {
        holder.bindItems(Singleton.arrayList[itemPosition])
        //this.position = position
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data: String) {

            itemView.textView.text = data
            //각각의 아이템 클릭시
        }

    }
}
