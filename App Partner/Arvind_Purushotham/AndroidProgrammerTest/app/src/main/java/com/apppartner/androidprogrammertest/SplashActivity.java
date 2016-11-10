package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
/*
        -------------------------- IMPORTANT MESSAGE -----------------------------
        Please view the developer notes, section splash for important observations
        --------------------------------------------------------------------------
*/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    // The other lifecycles of the activity are not required.
}
