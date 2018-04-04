package com.example.u772.testapp;

import android.os.AsyncTask;

import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by U772 on 02.04.2018.
 */

public class DownloadWeather extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        try{
            URL url = new URL("https://mgelios.pythonanywhere.com/api/v1/weather");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("content-type", "application/json; charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream inputStream;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK){
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null){
                response.append(line);
                response.append("\r");
            }
            reader.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println("exception occured!");
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
