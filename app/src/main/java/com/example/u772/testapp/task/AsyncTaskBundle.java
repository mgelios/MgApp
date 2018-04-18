package com.example.u772.testapp.task;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U772 on 13.04.2018.
 */

public class AsyncTaskBundle {

    private List<AsyncTask<String, Void, String>> taskList;

    public AsyncTaskBundle(){
        taskList = new ArrayList<>();
    }

    public void execute(){
        for (AsyncTask<String, Void, String> task : taskList){
            task.execute();
        }
    }

    public void addTask(AsyncTask task){
        taskList.add(task);
    }

}
