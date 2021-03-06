package com.example.freewrite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_mange_student_item.view.*
import java.util.ArrayList

class RecyclerViewAdapter(dL: MutableLiveData<ArrayList<String>>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var dataList = dL.value!!

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_mange_student_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(dataList[itemPosition])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItemStatusListItems(data: String){

            setTextViewsText(data)

            //각각의 아이템 클릭시
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, data, Toast.LENGTH_SHORT).show()
            }
        }

        private fun setTextViewsText(data: String) {
            itemView.textView_Name_ManageStudent_Item.text = data
        }

    }

}
