package com.example.listviewtest

object Singleton {

    lateinit var arrayList : ArrayList<String>
    var tempList = arrayListOf<String>("5", "4", "3", "2", "1")
    fun getList(): ArrayList<String>{
        return arrayList
    }

    fun setList(tempList: ArrayList<String>){
        arrayList = tempList
    }

    fun changeList(){
        arrayList = tempList
    }

}