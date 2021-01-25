package com.example.roomexample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface StudentDAO {
    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE name || age || classNum || gender LIKE :word ")
    fun search(word: String): List<Student>

    @Insert(onConflict = REPLACE)
    fun insert(student: Student)

    @Query("DELETE from student")
    fun deleteAll()


}