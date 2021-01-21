package com.example.chartexample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet


class MainActivity : AppCompatActivity() {

    lateinit var radarChart: RadarChart

    private fun dataValue(): ArrayList<RadarEntry>? {
        val dataVals: ArrayList<RadarEntry> = ArrayList()
        dataVals.add(RadarEntry(10f))
        dataVals.add(RadarEntry(20f))
        dataVals.add(RadarEntry(15f))
        dataVals.add(RadarEntry(30f))
        dataVals.add(RadarEntry(25f))
        return dataVals
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        radarChart = findViewById(R.id.radarChart)

        radarChart.setBackgroundColor(Color.WHITE)

        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.LTGRAY);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.LTGRAY);
        //radarChart.setWebAlpha(100);

        makeChart()
        //setData()
    }

    private fun makeChart() {
        val dataSet = RadarDataSet(dataValue(), "DATA")
        dataSet.fillColor = Color.argb(160, 168, 0, 0) //80A80000
        dataSet.setDrawFilled(true)

        val data = RadarData()
        data.addDataSet(dataSet)
        val labels = arrayOf("STR", "DEX", "INT", "LUK", "DEF")
        val xAxis = radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        radarChart.data = data

    }


    private fun setData() {
        val mul = 80f
        val min = 20f
        val cnt = 5
        val entries1: ArrayList<RadarEntry> = ArrayList()
        val entries2: ArrayList<RadarEntry> = ArrayList()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        //entries1.add(RadarEntry("STR"))
        for (i in 0 until cnt) {
            val val1 = (Math.random() * mul).toFloat() + min
            entries1.add(RadarEntry(val1))

            val val2 = (Math.random() * mul).toFloat() + min
            entries2.add(RadarEntry(val2))
        }
        val set1 = RadarDataSet(entries1, "Last Week")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.argb(124, 168, 0, 0) //80A80000
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)

        val sets: ArrayList<IRadarDataSet> = ArrayList()
        sets.add(set1)
        val data = RadarData(sets)
        //data.setValueTypeface(tfLight)
        data.setValueTextSize(8f)
        data.setDrawValues(false)

        data.setValueTextColor(Color.WHITE)
        radarChart.setData(data)
        radarChart.invalidate()
    }


}