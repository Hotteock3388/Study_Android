package com.example.retrofit2example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var mRetrofit: Retrofit? = null

    private var mRetrofitAPI: RetrofitAPI? = null

    //private var mCallMovieList: Call<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRetrofitInit()
        callMovieList()

    }

    private fun setRetrofitInit() {
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mRetrofitAPI = mRetrofit?.create(RetrofitAPI::class.java)

    }


    private fun callMovieList() {

        mRetrofitAPI?.getMovieList()?.enqueue(object : Callback<CUU>{
            override fun onResponse(call : Call<CUU>, response: Response<CUU>){
                var result: CUU? = response.body()

                if (result != null) {
                    Log.d("TestLog", result.login)
                }
                else{

                    Log.d("TestLog", "null!!")
                }
            }

            override fun onFailure(call: Call<CUU>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }


}
