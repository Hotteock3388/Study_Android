package com.example.retrofit2example

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitAPI {
    @GET("/users/hotteock3388")
    fun getMovieList(): Call<CUU>?
}

