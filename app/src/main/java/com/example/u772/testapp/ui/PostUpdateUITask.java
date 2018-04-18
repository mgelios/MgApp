package com.example.u772.testapp.ui;

import android.os.AsyncTask;
import android.view.View;

import com.example.u772.testapp.MainActivity;
import com.example.u772.testapp.R;

/**
 * Created by U772 on 13.04.2018.
 */

public class PostUpdateUITask extends AsyncTask<String, Void, String> {

    private MainActivity activity = null;

    private View updateImageView = null;

    public PostUpdateUITask(MainActivity mainActivity){
        activity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (activity != null) {
            updateImageView = activity.findViewById(R.id.header_update);
            updateImageView.clearAnimation();
        }
    }

}
