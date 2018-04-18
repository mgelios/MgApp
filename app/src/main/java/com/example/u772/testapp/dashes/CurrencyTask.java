package com.example.u772.testapp.dashes;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.u772.testapp.AppConstants;
import com.example.u772.testapp.CurrencyListAdapter;
import com.example.u772.testapp.ForecastListAdapter;
import com.example.u772.testapp.MainActivity;
import com.example.u772.testapp.R;
import com.example.u772.testapp.network.NetworkProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by U772 on 18.04.2018.
 */

public class CurrencyTask extends AsyncTask<String, Void, String> {

    private static final String CURRENCY_URL = "https://mgelios.pythonanywhere.com/api/v1/currency";

    private View currencyView = null;

    private LinearLayout currenciesContainer = null;

    private MainActivity activity = null;

    private CurrencyListAdapter currencyListAdapter = null;


    public CurrencyTask(MainActivity mainActivity){
        activity = mainActivity;
        LinearLayout listItems = (LinearLayout) activity.findViewById(R.id.listItems);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        currencyView = layoutInflater.inflate(R.layout.currency_row,null, false);
        listItems.addView(currencyView);
        currenciesContainer = (LinearLayout) activity.findViewById(R.id.currencies_container);
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = NetworkProcessor.doGet(CURRENCY_URL, "");
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonResponse = new JSONObject(s);

            List<Map<String, String>> dataList = new ArrayList<>();
            JSONArray rawCurrencies = jsonResponse.getJSONArray("currencies");
            JSONArray rawConverions = jsonResponse.getJSONArray("conversions");
            for (int i = 0; i < rawCurrencies.length(); i++){
                JSONObject tmpCurrency = (JSONObject) rawCurrencies.get(i);
                Map<String, String> tmpMap = new HashMap<>();
                tmpMap.put(AppConstants.KEY_NAME, tmpCurrency.getString("abbreviation"));
                tmpMap.put(AppConstants.KEY_VALUE, tmpCurrency.getString("rate"));
                dataList.add(tmpMap);
            }

            currencyListAdapter = new CurrencyListAdapter(activity, dataList);
            for(Map<String, String> item : dataList){
                View itemView = currencyListAdapter.getView(dataList.indexOf(item), null, currenciesContainer);
                currenciesContainer.addView(itemView);
            }

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
