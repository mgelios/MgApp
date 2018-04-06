package com.example.u772.testapp.dashes;

import java.util.Date;

/**
 * Created by U772 on 06.04.2018.
 */

public class ForecastModel {

    private String city = "";

    private String requestedCity = "";

    private int temperature = 0;

    private int minTemperature = 0;

    private int maxTemperature = 0;

    private int pressure = 0;

    private int humidity = 0;

    private String info = "";

    private String description = "";

    private String icon = "";

    private double windSpeed = 0.0;

    private double windDeg = 0.0;

    private Date date = null;





    public String getCity() {
        return city;
    }

    public String getRequestedCity() {
        return requestedCity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
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

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public Date getDate() {
        return date;
    }
}
