package com.nflux.crawling

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nflux.crawling.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private fun showLog(msg: String) = Log.d("TestLog", msg)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        test()
        doCrawlingFitbit()
    }

    fun test(){
        CoroutineScope(IO).launch {
            var response = Jsoup.connect("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com")
                .method(Connection.Method.GET)
                .execute()

            showLog("naver - cookies = ${response.cookies()}")

            response = Jsoup.connect("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com")
                .data("id", "andy3388")
                .data("pw", "andy1029")
                .cookies(response.cookies())
                .method(Connection.Method.POST)
                .execute()

            val homePage = Jsoup.connect("https://www.naver.com/")
                .cookies(response.cookies())
                .get()

        }
    }

    fun doCrawlingFitbit(){

        var doc : Document
        var loginTryCookie : Map<String, String> = HashMap()

        val data: MutableMap<String, String> = HashMap()

        var loginCookie : Map<String, String> = HashMap()

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            launch {
                withContext(IO) {
                    showLog("run j1")

//                    val loginPageResponse  = Jsoup.connect("https://accounts.fitbit.com/login")
//                        .timeout(3000)
//                        .header("Accept", "application/json, text/javascript, */*; q=0.01")
//                        .header("Content-Type", "application/x-www-form-urlencoded")
//                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
//                        .header("sec-ch-ua", "Not?A_Brand\";v=\"8\", \"Chromium\";v=\"108\", \"Google Chrome\";v=\"108")
//                        .header("sec-ch-ua-mobile", "?0")
//                        .header("sec-ch-ua-platform", "Windows")
//                        .method(Connection.Method.POST)
//                        .execute()

                val loginPageResponse  = Jsoup.connect("https://accounts.fitbit.com/login")
                    .timeout(3000)
                    .header("Origin", "https://accounts.fitbit.com")
                    .header("Refer", "https://accounts.fitbit.com/")
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                    .method(Connection.Method.GET)
                    .execute()

                    delay(1500)
//                    showLog("loginPageResponse = ${loginPageResponse.body()}")

//                    doc = loginPageResponse.parse()
//                    showLog("loginTry Doc Text = ${doc.text()}")

                    loginTryCookie = loginPageResponse.cookies()

                    showLog("loginTryCookie = $loginTryCookie")

                    data["ember665"] = "hyosunyo3388@gmail.com"
                    data["ember666"] = "andy3389!@"

                    showLog("finish j1")
                }

                withContext(IO) {
                    showLog("run j2")

                    val r = Jsoup.connect("https://accounts.fitbit.com/login")
                        .data("ember665", "hyosunyo3388@gmail.com", "ember666", "andy3389!@")
                        .method(Connection.Method.POST)
                        .execute()

                    showLog("r.cookies() = ${r.cookies()}")


                    val response = Jsoup.connect("https://accounts.fitbit.com/login")
                        .timeout(3000)
                        .ignoreHttpErrors(true)
                        .header("Origin", "https://accounts.fitbit.com")
                        .header("Refer", "https://accounts.fitbit.com/")
                        .header("Accept", "application/json, text/javascript, */*; q=0.01")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                        .data(data)
                        .method(Connection.Method.POST)
                        .execute()

                    delay(1500)

                    val d = response.parse()
//                    showLog("d = ${d.text()}")
                    loginCookie = response.cookies()
                    showLog("loginCookie = $loginCookie")

                    showLog("finish j2")
                }

                withContext(IO) {
                   showLog("run j3")
                    val successResponse = Jsoup.connect("https://www.fitbit.com/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                        .header("Refer", "https://accounts.fitbit.com/")
                        .header("Accept", "application/json, text/javascript, */*; q=0.01")
                        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .cookies(loginCookie)
                        .get()

                   showLog("finish j3")
//                    Log.d("TestLog_successResponse.body()", "${successResponse.body()}")
                }

//
//                async {
//
//
////                val loginPageResponse  = Jsoup.connect("https://accounts.fitbit.com/login")
////                    .timeout(3000)
////                    .header("Origin", "https://accounts.fitbit.com")
////                    .header("Refer", "https://accounts.fitbit.com/")
////                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
////                    .header("Accept-Encoding", "gzip, deflate, br")
////                    .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
////                    .header("Content-Type", "application/x-www-form-urlencoded")
////                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
////                    .method(Connection.Method.GET)
////                    .execute()
//
//
//
////        data["redirectUrl"] = "http://tistory.com/"
//
//
//
//
//
//                }.await()


//                j1.await()
//                j2.await()
//                j3.await()
                showLog("after Await")
            }.join()

        }

    }

    fun doCrawlingLottery() {

        CoroutineScope(IO).launch {

            val t =  Jsoup.connect("https://dhlottery.co.kr/common.do?method=main")
            val doc = Jsoup.connect("https://dhlottery.co.kr/common.do?method=main").get()


            val contents = doc.select("#lottoDrwNo")
            var nums = "${contents.text()} 회  :"

            repeat(6) {

            val content = doc.select("#drwtNo${it + 1}")
                nums += " ${content.text()} "
            }
            nums += doc.select("#bnusNo").text()

            withContext(Main){
                binding.textView.text = nums

            }

        }
    }


}