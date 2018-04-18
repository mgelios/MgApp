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

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.setForecastContainer(forecastContainer);
        weatherTask.setWeatherView(weatherView);
        weatherTask.execute();
    }

}
