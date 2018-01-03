package com.example.oliver.sensoren;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;
    Sensor accelerometer, gyroscope, magneticField, proximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magneticField = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);

        TextView tv = (TextView) findViewById(R.id.liste);

        // Liste mit Sensoren erstellen und im Textfeld ausgeben
        List<Sensor> sensorliste = sm.getSensorList(Sensor.TYPE_ALL);
        String sensor = "";
        for(Sensor s : sensorliste) {
            sensor += s.getName() + "\n";
        }
        tv.setText(sensor);
    }

    @Override
    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if(accelerometer != null) {
                String werte = "";
                for(int i = 0; i < sensorEvent.values.length; i++) {
                    werte += sensorEvent.values[i] + "\n";
                }
                TextView tv = (TextView) findViewById(R.id.sensor1);
                tv.setText(werte);
            }
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            if(gyroscope != null) {
                String werte = "";
                for(int i = 0; i < sensorEvent.values.length; i++) {
                    werte += sensorEvent.values[i] + "\n";
                }
                TextView tv = (TextView) findViewById(R.id.sensor2);
                tv.setText(werte);
            }
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            if(magneticField != null) {
                String werte = "";
                for(int i = 0; i < sensorEvent.values.length; i++) {
                    werte += sensorEvent.values[i] + "\n";
                }
                TextView tv = (TextView) findViewById(R.id.sensor3);
                tv.setText(werte);
            }
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if(proximity != null) {
                String werte = "";
                for(int i = 0; i < sensorEvent.values.length; i++) {
                    werte += sensorEvent.values[i] + "\n";
                }
                TextView tv = (TextView) findViewById(R.id.sensor4);
                tv.setText(werte);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
