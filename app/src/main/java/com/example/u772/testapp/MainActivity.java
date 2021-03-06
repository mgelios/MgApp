package com.example.u772.testapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.u772.testapp.dashes.CurrencyTask;
import com.example.u772.testapp.dashes.Weather;
import com.example.u772.testapp.dashes.WeatherTask;
import com.example.u772.testapp.task.AsyncTaskBundle;
import com.example.u772.testapp.ui.PostUpdateUITask;
import com.example.u772.testapp.ui.PreUpdateUITask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private AsyncTaskBundle updateMainScreen = new AsyncTaskBundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateMainScreen.addTask(new PreUpdateUITask(MainActivity.this));
        updateMainScreen.addTask(new WeatherTask(MainActivity.this));
        updateMainScreen.addTask(new CurrencyTask(MainActivity.this));
        updateMainScreen.addTask(new PostUpdateUITask(MainActivity.this));
        updateMainScreen.execute();
    }

    public void updateImageClick(View view){
        if (view.getAnimation() == null){
            updateMainScreen.clearTasks();
            updateMainScreen.addTask(new PreUpdateUITask(MainActivity.this));
            updateMainScreen.addTask(new WeatherTask(MainActivity.this));
            updateMainScreen.addTask(new CurrencyTask(MainActivity.this));
            updateMainScreen.addTask(new PostUpdateUITask(MainActivity.this));
            updateMainScreen.execute();
        }
    }



}
