package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student(@PrimaryKey var classNum: Int,
              @ColumnInfo(name = "name") var name: String,
              @ColumnInfo(name = "age") var age: Int,
              @ColumnInfo(name = "gender") var gender: String)
{
    constructor(): this("0000".toInt(), "호떡", 19, "남")
}