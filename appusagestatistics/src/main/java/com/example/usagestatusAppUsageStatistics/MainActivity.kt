package com.example.usagestatusAppUsageStatistics


import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.util.Log
import android.util.LongSparseArray
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String
import java.util.*
import kotlin.collections.ArrayList


data class UsageStats(
    var appName : kotlin.String,
    var appIcon : Drawable,
    var useTime: kotlin.String
)


class MainActivity : AppCompatActivity() {

    //var usagestatslist : List<com.example.usagestatusAppUsageStatistics.UsageStats> = List<com.example.usagestatusAppUsageStatistics.UsageStats>
    var usagestatslist = ArrayList<com.example.usagestatusAppUsageStatistics.UsageStats>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var queryUsageStats : List<UsageStats>
        //(intervalType: Int, beginTime: Long, endTime: Long)

        if (!checkForPermission()) {
            Log.i("TestLog", "The user may not allow the access to apps usage. ")
            Toast.makeText(
                this,
                "Failed to retrieve app usage statistics. " +
                        "You may need to enable access for this app through " +
                        "Settings > Security > Apps with usage access",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            // We have the permission. Query app usage stats.
            Toast.makeText(applicationContext, "권한 획득 완료!", Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            queryUsageStats = getAppUsageStats()
            showAppUsageStats(queryUsageStats as MutableList<UsageStats>)
            initListView()
            //textView.text = getTopPackageName(applicationContext)
        }

    }

    private fun initListView() {
        var adapter = ListViewAdapter(applicationContext, usagestatslist)
        listView.adapter = adapter
        usagestatslist
        adapter.notifyDataSetChanged()
    }

    private fun checkForPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), packageName)
        return mode == MODE_ALLOWED
    }

    private fun getAppUsageStats(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -1)    // 1

        val usageStatsManager =
            getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager // 2

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY, cal.timeInMillis, System.currentTimeMillis() // 3
        )

    }

    //@RequiresApi(Build.VERSION_CODES.Q)
    private fun showAppUsageStats(usageStats: MutableList<UsageStats>) {
        usageStats.sortWith(Comparator { right, left ->
            compareValues(left.lastTimeUsed, right.lastTimeUsed)
        })

        usageStats.forEach { it ->
            if(it.totalTimeVisible > 1000) {
                var usageStats: com.example.usagestatusAppUsageStatistics.UsageStats =
                    com.example.usagestatusAppUsageStatistics.UsageStats(
                        "",
                        this?.getDrawable(R.drawable.ic_launcher_foreground)!!,
                        ""
                    )
                usageStats.appName = it.packageName
                try {
                    usageStats.appIcon = packageManager.getApplicationIcon(it.packageName)

                } catch (e: PackageManager.NameNotFoundException) {
                    Log.d(
                        //com.example.android.appusagestatistics.AppUsageStatisticsFragment.TAG
                        "TestLog",
                        String.format(
                            "App Icon is not found for %s",
                            it.getPackageName()

                        )
                    )
                    usageStats.appIcon = this?.getDrawable(R.drawable.ic_launcher_foreground)!!
                }

                Log.d(
                    "TestLog",
                    "packageName: ${it.packageName}, lastTimeUsed: ${Date(it.lastTimeUsed)}, totalTimeInForeground: ${it.totalTimeInForeground}"
                )
                usageStats.useTime = "${it.totalTimeVisible / 1000} 초"
                    usagestatslist.add(usageStats)
            }
        }
    }
}