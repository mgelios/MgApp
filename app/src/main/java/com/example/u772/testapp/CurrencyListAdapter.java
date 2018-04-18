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

/**
 * Created by U772 on 18.04.2018.
 */

public class CurrencyListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Map<String, String>> data;

    public CurrencyListAdapter(Activity a, List<Map<String, String>> list){
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
        CurrencyItemHolder holder = null;
        if (view == null){
            holder = new CurrencyItemHolder();
            view = LayoutInflater
                    .from(activity)
                    .inflate(R.layout.currency_item, viewGroup, false);
            holder.name = (TextView) view.findViewById(R.id.currency_name);
            holder.value = (TextView) view.findViewById(R.id.currency_value);
            view.setTag(holder);
        } else {
            holder = (CurrencyItemHolder) view.getTag();
        }
        holder.name.setId(i);
        holder.value.setId(i);
        Map<String, String> dataItem = new HashMap<String, String>();
        dataItem = data.get(i);

        try {
            holder.name.setText(dataItem.get(AppConstants.KEY_NAME));
            holder.value.setText(dataItem.get(AppConstants.KEY_VALUE));
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
