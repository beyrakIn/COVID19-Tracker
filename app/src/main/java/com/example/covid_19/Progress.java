package com.example.covid_19;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;

public class Progress {
    Activity activity;
    ProgressDialog progressDialog;

    public Progress(Activity activity) {
        this.activity = activity;
    }

    void start() {
        LayoutInflater inflater = activity.getLayoutInflater();
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(inflater.inflate(R.layout.progress, null));
    }

    void stop() {
        progressDialog.dismiss();
    }
}
