package com.example.u772.testapp;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForecastListAdapter extends BaseAdapter{

    private Activity activity;
    private List<Map<String, String>> data;

    public ForecastListAdapter(Activity a, List<Map<String, String>> list){
        activity = a;
        data = list;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ForecastItemHolder holder = null;
        if (view == null){
            holder = new ForecastItemHolder();
            view = LayoutInflater
                    .from(activity)
                    .inflate(R.layout.forecast_item, viewGroup, false);
            holder.date = (TextView) view.findViewById(R.id.forecast_date);
            holder.maxTemp = (TextView) view.findViewById(R.id.forecast_max_temp);
            holder.minTemp = (TextView) view.findViewById(R.id.forecast_min_temp);
            view.setTag(holder);
        } else {
            holder = (ForecastItemHolder) view.getTag();
        }
        holder.date.setId(i);
        holder.maxTemp.setId(i);
        holder.minTemp.setId(i);
        Map<String, String> dataItem = new HashMap<String, String>();
        dataItem = data.get(i);

        try {
            holder.date.setText(dataItem.get(AppConstants.KEY_DATE));
            holder.minTemp.setText(dataItem.get(AppConstants.KEY_MIN_TEMP));
            holder.maxTemp.setText(dataItem.get(AppConstants.KEY_MAX_TEMP));
        } catch (Exception e){

        }

        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
