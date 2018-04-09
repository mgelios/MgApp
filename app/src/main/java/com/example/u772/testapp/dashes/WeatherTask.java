package com.example.u772.testapp.dashes;

import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.example.u772.testapp.AppConstants;
import com.example.u772.testapp.ForecastListAdapter;
import com.example.u772.testapp.MainActivity;
import com.example.u772.testapp.R;
import com.example.u772.testapp.network.NetworkProcessor;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by U772 on 02.04.2018.
 */

public class WeatherTask extends AsyncTask<String, Void, String> {

    private View weatherView = null;

    private LinearLayout forecastContainer = null;

    private View updateImageView = null;

    private MainActivity activity = null;

    private static final String WEATHER_URL = "https://mgelios.pythonanywhere.com/api/v1/weather";

    private Animation rotateAnimation = null;

    private ForecastListAdapter forecastAdapter = null;

    @Override
    protected void onPreExecute(){
        rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotation);
        updateImageView.startAnimation(rotateAnimation);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = NetworkProcessor.doGet(WEATHER_URL, "");
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonResponse = new JSONObject(s);
            WeatherModel weatherModel = new WeatherModel(jsonResponse);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            TextView mainTempView = weatherView.findViewById(R.id.weather_main_temp);
            TextView mainInfo = weatherView.findViewById(R.id.weather_main_info);
            TextView humidity = weatherView.findViewById(R.id.weather_humidity);
            TextView wind = weatherView.findViewById(R.id.weather_wind);
            TextView visibility = weatherView.findViewById(R.id.weather_visibility);
            TextView sunrise = weatherView.findViewById(R.id.weather_sunrise);
            TextView sunset = weatherView.findViewById(R.id.weather_sunset);

            List<Map<String, String>> dataList = new ArrayList<>();
            for (ForecastModel forecast : weatherModel.getForecasts()){
                Map<String, String> tmpMap = new HashMap<>();
                tmpMap.put(AppConstants.KEY_DATE, forecast.getDate());
                tmpMap.put(AppConstants.KEY_MIN_TEMP, String.valueOf(forecast.getMinTemp())+AppConstants.TEMPERATURE_SUFFIX);
                tmpMap.put(AppConstants.KEY_MAX_TEMP, String.valueOf(forecast.getMaxTemp())+AppConstants.TEMPERATURE_SUFFIX);
                dataList.add(tmpMap);
            }

            forecastAdapter = new ForecastListAdapter(activity, dataList);
            for(Map<String, String> item : dataList){
                View itemView = forecastAdapter.getView(dataList.indexOf(item), null, forecastContainer);
                forecastContainer.addView(itemView);
            }

            mainTempView.setText(String.valueOf(weatherModel.getTemperature()) + AppConstants.TEMPERATURE_SUFFIX);
            mainInfo.setText(weatherModel.getDescription());
            humidity.setText("влажность: " + String.valueOf(weatherModel.getHumidity()) + "%");
            wind.setText("ветер: " + String.valueOf(weatherModel.getWindSpeed()) + " м/сек");
            visibility.setText("видимость: " + String.valueOf(weatherModel.getVisibility()) + "м");
            sunrise.setText("восход: " + timeFormat.format(weatherModel.getSunrise()));
            sunset.setText("закат: " + timeFormat.format(weatherModel.getSunset()));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } finally {
            updateImageView.clearAnimation();
        }
    }

    private List<ForecastModel> fillForecasts(JSONArray rawForecasts){

        return null;
    }

    public void setWeatherView(View weatherView) {
        this.weatherView = weatherView;
    }

    public void setForecastContainer(LinearLayout forecastContainer) {
        this.forecastContainer = forecastContainer;
    }

    public void setUpdateImageView(View updateImageView) {
        this.updateImageView = updateImageView;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }
}
