package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    private fun showLog(msg: String) = Log.d("TestLog", msg)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(IO).launch {
            te("A")
            te("BB")
        }


        lifecycleScope.launch{

        }
    }

    suspend fun te(msg: String){
        val scope = GlobalScope

        val a = scope.async {
            repeat(100){
                showLog("A $it")
            }
            2
        }.await()


        val b = scope.async {
            repeat(100){
                showLog("BB $it")
            }
            2
        }.await()
    }


    suspend fun function() = coroutineScope() {
        // ...
    }

    private suspend fun t(){
        coroutineScope {
            launch {  }
        }
    }

    fun exampleSuspend(){
        val job3 = CoroutineScope(Dispatchers.IO).async {
            showLog("job3")
            // 2. IO Thread에서 작업3를 수행한다.
            (1..10000).sortedByDescending { it }
            // 5. 작업3가 완료된다.
        }

        val job1 = CoroutineScope(Dispatchers.Main).launch {
            // 1. Main Thread에서 작업1을 수행한다.
            showLog("1")

            // 3. 작업1의 남은 작업을 위해 작업3로부터 결과값이 필요하기 때문에 Main Thread는 작업1을 일시중단한다.
            val job3Result = job3.await()
            // 6. 작업3로부터 결과를 전달받는다.
            // 7. 작업1이 재개된다.
            job3Result.forEach {
                showLog("$it")
            }
        }


        // 4. Main Thread에서 작업2이 수행되고 완료된다.
        val job2 = CoroutineScope(Dispatchers.Main).launch {
            showLog("Job2 수행완료")
        }
    }

    private fun test(){
        val a = 2 + 5
        showLog("a = $a")
    }

}