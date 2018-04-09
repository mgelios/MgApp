package com.example.u772.testapp.dashes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by U772 on 06.04.2018.
 */

public class WeatherModel {

    private String info = "";

    private String description = "";

    private String icon = "";

    private String cityName = "";

    private String requestedCity = "";

    private int temperature = 0;

    private int humidity = 0;

    private int pressure = 0;

    private int visibility = 0;

    private int minTemperature = 0;

    private int maxTemperature = 0;

    private double windSpeed = 0.0;

    private double windDeg = 0.0;

    private Date sunrise = null;

    private Date sunset = null;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzzzz");

    private List<ForecastModel> forecasts = new ArrayList<>();

    public WeatherModel(){
    }


    public WeatherModel(JSONObject jsonResponse){
        try {
            JSONObject rawWeather = (JSONObject) jsonResponse.get("weather");
            info = rawWeather.getString("main_info");
            description = rawWeather.getString("description");
            icon = rawWeather.getString("icon_name");
            cityName = rawWeather.getString("city_name");
            requestedCity = rawWeather.getString("requested_city");
            temperature = rawWeather.getInt("temperature");
            humidity = rawWeather.getInt("humidity");
            pressure = rawWeather.getInt("pressure");
            visibility = rawWeather.getInt("visibility");
            minTemperature = rawWeather.getInt("temperature_min");
            maxTemperature = rawWeather.getInt("temperature_max");
            windSpeed = rawWeather.getDouble("wind_speed");
            windDeg = rawWeather.getDouble("wind_deg");
            sunrise = dateFormat.parse(rawWeather.getString("sunrise"));
            sunset = dateFormat.parse(rawWeather.getString("sunset"));
            initForecasts((JSONArray) jsonResponse.getJSONArray("forecast"));
        } catch (JSONException e){
            System.out.println(e.getMessage());
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }

    private void initForecasts(JSONArray rawForecasts){
        Set<String> dates = new TreeSet<String>();
        SimpleDateFormat forecastDate = new SimpleDateFormat("E dd");
        SimpleDateFormat forecastComparisonDate = new SimpleDateFormat("DDD");

        try {
            for (int i = 0; i < rawForecasts.length(); i++) {
                JSONObject rawForecast = (JSONObject) rawForecasts.get(i);
                Date tmpDate = dateFormat.parse(rawForecast.getString("date_time"));
                String strTmpDate = forecastComparisonDate.format(tmpDate);
                dates.add(strTmpDate);
            }
            for (String date : dates){
                ForecastModel forecast = new ForecastModel();
                for (int i = 0; i < rawForecasts.length(); i++){
                    JSONObject rawForecast = (JSONObject) rawForecasts.get(i);
                    Date tmpDate = dateFormat.parse(rawForecast.getString("date_time"));
                    String strTmpDate = forecastComparisonDate.format(tmpDate);
                    if (date.equals(strTmpDate)){
                        forecast.setDate(forecastDate.format(tmpDate));
                        int forecastTemperature = rawForecast.getInt("temperature");
                        int forecastPressure = rawForecast.getInt("pressure");
                        int forecastWindSpeed = rawForecast.getInt("wind_speed");
                        if (forecast.getMaxTemp() < forecastTemperature){
                            forecast.setMaxTemp(forecastTemperature);
                        }
                        if (forecast.getMinTemp() > forecastTemperature){
                            forecast.setMinTemp(forecastTemperature);
                        }
                        forecast.setPressure(forecastPressure);
                        forecast.setWindSpeed(forecastWindSpeed);
                    }
                }
                forecasts.add(forecast);
            }

        } catch (JSONException e){
            System.out.println(e.getMessage());
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getCityName() {
        return cityName;
    }

    public String getRequestedCity() {
        return requestedCity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public List<ForecastModel> getForecasts() {
        return forecasts;
    }
}
