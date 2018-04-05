package com.example.u772.testapp.dashes;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.u772.testapp.R;
import com.example.u772.testapp.network.NetworkProcessor;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by U772 on 02.04.2018.
 */

public class WeatherTask extends AsyncTask<String, Void, String> {

    private View weatherView = null;

    private LinearLayout forecastContainer = null;

    private static final String WEATHER_URL = "https://mgelios.pythonanywhere.com/api/v1/weather";

    @Override
    protected void onPreExecute(){
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
            TextView mainTempView = weatherView.findViewById(R.id.weather_main_temp);
            int mainTemp = ((JSONObject) jsonResponse.get("weather")).getInt("temperature");
            mainTempView.setText(String.valueOf(mainTemp));
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setWeatherView(View weatherView) {
        this.weatherView = weatherView;
    }

    public void setForecastContainer(LinearLayout forecastContainer) {
        this.forecastContainer = forecastContainer;
    }
}
