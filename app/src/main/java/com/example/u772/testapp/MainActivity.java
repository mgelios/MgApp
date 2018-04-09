package com.example.u772.testapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.u772.testapp.dashes.Weather;
import com.example.u772.testapp.dashes.WeatherTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout listItems = (LinearLayout) findViewById(R.id.listItems);
        LayoutInflater layoutInflater = getLayoutInflater();
        View updateImageView = findViewById(R.id.header_update);
        View weatherView = layoutInflater.inflate(R.layout.weather_row,null, false);
        listItems.addView(weatherView);

        LinearLayout forecastContainer = (LinearLayout) findViewById(R.id.forecast_container);

        Weather weather = new Weather();
        weather.processMainActivity(MainActivity.this, weatherView, forecastContainer, updateImageView);

    }



}
