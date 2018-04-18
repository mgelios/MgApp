package com.example.u772.testapp.ui;

import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.u772.testapp.MainActivity;
import com.example.u772.testapp.R;

/**
 * Created by U772 on 13.04.2018.
 */

public class PreUpdateUITask extends AsyncTask<String, Void, String> {

    private MainActivity activity = null;

    private View updateImageView = null;

    private Animation rotateAnimation = null;

    public PreUpdateUITask(MainActivity mainActivity){
        activity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        if (activity != null) {
            updateImageView = activity.findViewById(R.id.header_update);
            rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotation);
            updateImageView.startAnimation(rotateAnimation);
        }
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
