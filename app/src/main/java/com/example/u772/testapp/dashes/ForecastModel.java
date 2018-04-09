package com.example.u772.testapp.dashes;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by U772 on 06.04.2018.
 */

public class ForecastModel {

    private String date = "";

    private int maxTemp = -2147000000;

    private int minTemp = 2147000000;

    private int pressure = 0;

    private int windSpeed = 0;

    private String description = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
