package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(@PrimaryKey var classNum: Int,
                   var name: String,
                   var age: Int,
                   var gender: String)