package com.example.u772.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
        View weatherView = layoutInflater.inflate(R.layout.weather_row,null, false);
        listItems.addView(weatherView);

        LinearLayout forecastContainer = (LinearLayout) findViewById(R.id.forecast_container);

        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        for (Map<String, String> item : dataList){
            item.put(AppConstants.KEY_DATE, "Tue 09");
            item.put(AppConstants.KEY_MIN_TEMP, "-14   C");
            item.put(AppConstants.KEY_MAX_TEMP, "+12   C");
        }

        ForecastListAdapter forecastAdapter = new ForecastListAdapter(MainActivity.this, dataList);
        for(Map<String, String> item : dataList){
            View itemView = forecastAdapter.getView(dataList.indexOf(item), null, forecastContainer);
            forecastContainer.addView(itemView);
        }
    }

}
