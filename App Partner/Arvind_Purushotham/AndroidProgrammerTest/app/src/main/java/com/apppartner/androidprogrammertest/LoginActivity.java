package com.apppartner.androidprogrammertest;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.apppartner.androidprogrammertest.controllers.CustomToolbarSvc;
import com.apppartner.androidprogrammertest.controllers.LoginJSONRequestSvc;
import com.apppartner.androidprogrammertest.models.MachinatoFont;

/*
        -------------------------- IMPORTANT MESSAGE -----------------------------
        Please view the developer notes, section 3.0 for important observations
        --------------------------------------------------------------------------
*/

public class LoginActivity extends AppCompatActivity {
    private EditText et_login_username;
    private EditText et_login_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        CustomToolbarSvc toolbar = new CustomToolbarSvc(this, "Login");
        toolbar.activateToolbar();
        AssetManager manage = getApplicationContext().getAssets();
        et_login_username.setTypeface(MachinatoFont.getRegular(manage));
        et_login_pwd.setTypeface(MachinatoFont.getRegular(manage));
    }

    // The other lifecycles of the activity are not required.

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onBackButtonPressed(View v) {
        finish();
    }

    public void login(View view) {
        LoginJSONRequestSvc login = new LoginJSONRequestSvc("http://dev3.apppartner.com/AppPartnerDeveloperTest/scripts/login.php",
                et_login_username.getText().toString(),
                et_login_pwd.getText().toString(),
                this);
        login.execute();
    }
}
