package com.example.u772.testapp.network;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by U772 on 05.04.2018.
 */

public class NetworkProcessor {

    public static boolean isNetworkAvailable(Context context){
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    public static String doGet(String address, String parameters){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(address);
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

}
