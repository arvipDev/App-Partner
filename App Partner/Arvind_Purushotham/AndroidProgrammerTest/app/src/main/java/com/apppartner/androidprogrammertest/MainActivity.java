package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.models.DeviceDetails;
import com.apppartner.androidprogrammertest.models.MachinatoFont;

/*
        -------------------------- IMPORTANT MESSAGE -----------------------------
        Please view the developer notes, section 1.0 for important observations
        --------------------------------------------------------------------------
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gathering device information - Usefull for rendering appropriate xml on screen based
        // on differences in screen size

        DeviceDetails deviceDetails = new DeviceDetails();
        deviceDetails.setDeiceModel(Build.MODEL);
        deviceDetails.setApiLevel(Build.VERSION.SDK_INT);
        getDisplayScreenSize(deviceDetails);

        setContentView(R.layout.activity_main);

        TextView tv_main = (TextView) findViewById(R.id.tv_main);
        tv_main.setTypeface(getCustomFont());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main Sctivity", " resumed");
    }

    // Other lifecycles of the activity are not required.

    public void onLoginButtonClicked(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onChatButtonClicked(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void onAnimationTestButtonClicked(View v) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    private void getDisplayScreenSize(DeviceDetails deviceDetails) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceDetails.setScreenHeight(size.y);
        deviceDetails.setScreenWidth(size.x);
    }

    private Typeface getCustomFont() {
        AssetManager manage = getApplicationContext().getAssets();
        return MachinatoFont.getBold(manage);
    }

}
