package com.example.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    final Integer TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setActionBar();
        setSplash();
    }

    private void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void setSplash() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, TIME);
    }

}