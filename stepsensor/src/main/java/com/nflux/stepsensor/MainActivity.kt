package com.nflux.stepsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_UI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private fun showLog(msg: String) = Log.d("TestLog_MainActivity", msg)
    private fun showToast(msg: String) = Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        sensorManager.registerListener( object: SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {

                if(event?.sensor?.type == Sensor.TYPE_STEP_COUNTER){
                    showLog("steps = ${event?.values?.get(0)}")
                    showToast("steps = ${event?.values?.get(0)}")
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }, stepCountSensor, SENSOR_DELAY_UI)

    }

}