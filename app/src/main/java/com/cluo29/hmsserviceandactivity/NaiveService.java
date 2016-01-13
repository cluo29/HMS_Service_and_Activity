package com.cluo29.hmsserviceandactivity;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by CLUO29 on 1/12/16.
 */
public class NaiveService extends Service implements SensorEventListener {


    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float accelerometer_x = event.values[0];
            float accelerometer_y = event.values[1];
            float accelerometer_z = event.values[2];

            Log.d("SENSORS", "x= " + accelerometer_x);
            Log.d("SENSORS", "y= " + accelerometer_y);
            Log.d("SENSORS", "z= " + accelerometer_z);
            Intent intent = new Intent("my.action.string");
            intent.putExtra("extra", accelerometer_x);
            sendBroadcast(intent);
        }
    }
    //get a thread to collect real time accelerometer without using AWARE
    /**
     * Sensor update frequency in microseconds, default 200000
     */

    private static int SAMPLING_RATE = 200000; //200000= 0.2sec, 20000=20ms
    private static SensorManager mSensorManager;
    private static Sensor mAccelerometer;

    @Override
    public void onCreate() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer, SAMPLING_RATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
