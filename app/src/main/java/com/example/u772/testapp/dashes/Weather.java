package com.example.u772.testapp.dashes;

import android.view.View;
import android.widget.LinearLayout;

import com.example.u772.testapp.AppConstants;
import com.example.u772.testapp.ForecastListAdapter;
import com.example.u772.testapp.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by U772 on 05.04.2018.
 */

public class Weather {

    public void processMainActivity(MainActivity mainActivity,
                                    View weatherView,
                                    LinearLayout forecastContainer,
                                    View updateImageView){
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        dataList.add(new HashMap<String, String>());
        for (Map<String, String> item : dataList){
            item.put(AppConstants.KEY_DATE, "Tue 00");
            item.put(AppConstants.KEY_MIN_TEMP, "-0   C");
            item.put(AppConstants.KEY_MAX_TEMP, "+0   C");
        }

        ForecastListAdapter forecastAdapter = new ForecastListAdapter(mainActivity, dataList);
        for(Map<String, String> item : dataList){
            View itemView = forecastAdapter.getView(dataList.indexOf(item), null, forecastContainer);
            forecastContainer.addView(itemView);
        }

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.setForecastContainer(forecastContainer);
        weatherTask.setWeatherView(weatherView);
        weatherTask.setUpdateImageView(updateImageView);
        weatherTask.setActivity(mainActivity);
        weatherTask.execute();
    }

}
