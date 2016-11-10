package com.apppartner.androidprogrammertest.controllers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.LoginResponse;
import com.apppartner.androidprogrammertest.models.MachinatoFont;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginJSONRequestSvc extends AsyncTask<String, Void, String> {
    private String username = null;
    private String password = null;
    private String url = null;
    private LoginResponse responseJSON = new LoginResponse();
    private AppCompatActivity activity = null;
    private int HttpResult = 0;

    public LoginJSONRequestSvc(String url, String username, String password, AppCompatActivity activity) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.activity = activity;
        Log.d("here ", username + password);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            if (username.equals("") || password.equals("")) {
                username = "a";
                password = "b";
            }
            jsonPOST(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "executed";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        alertDialog();
    }

    private void jsonPOST(String address) throws IOException {
        HttpURLConnection connection;
        OutputStreamWriter writer;

        URL url;
        String response;
        String parameters = "username=" + this.username + "&password=" + this.password;

        url = new URL(address);
        long initialTime = System.currentTimeMillis();
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.connect();

        writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(parameters);
        writer.flush();
        writer.close();
        String line;

        try {
            connection.getResponseCode();
        } catch (IOException e) {
            HttpResult = connection.getResponseCode();
        }
        if (HttpResult != HttpURLConnection.HTTP_UNAUTHORIZED) {
            HttpResult = connection.getResponseCode();
            Log.d("Json response ", "" + HttpResult + " " + connection.getResponseMessage());
        }

        if (HttpResult == HttpURLConnection.HTTP_OK) {
            InputStreamReader readInput = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(readInput);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            Log.d("response", response);
            readInput.close();
            reader.close();
            JSONObject jsonToPOJO;
            try {
                jsonToPOJO = new JSONObject(response);
                responseJSON.setMessage(jsonToPOJO.getString("message"));
                responseJSON.setCode(jsonToPOJO.getString("code"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("POJO ", responseJSON.getCode() + responseJSON.getMessage());
        } else {
            Log.d("Server error ", connection.getResponseMessage());
            responseJSON.setCode("Error");
            responseJSON.setMessage("Incorrect Username or Password!");
        }
        connection.disconnect();
        long finalTime = System.currentTimeMillis();
        if (responseJSON != null) {
            responseJSON.setDuration(finalTime - initialTime);
        }
    }

    private void alertDialog() {
        LayoutInflater li = LayoutInflater.from(activity);
        Context context = activity.getApplicationContext();
        final View loginDialog = li.inflate(R.layout.custom_alert_dialog, new LinearLayout(context), false);
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                activity);

        alertDialogBuilder.setView(loginDialog);
        final android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        AssetManager manage = activity.getAssets();

        TextView tv_alert_time = (TextView) loginDialog.findViewById(R.id.tv_alert_time);
        String text = "API call duration - ".concat(String.valueOf(responseJSON.getDuration())).concat(" milliseconds");
        tv_alert_time.setText(text);
        tv_alert_time.setTypeface(MachinatoFont.getSemiBoldItalic(manage));

        TextView tv_alert_message = (TextView) loginDialog.findViewById(R.id.tv_alert_message);
        text = "Message - ".concat(responseJSON.getMessage());
        tv_alert_message.setText(text);
        tv_alert_message.setTypeface(MachinatoFont.getSemiBoldItalic(manage));

        TextView tv_alert_code = (TextView) loginDialog.findViewById(R.id.tv_alert_code);
        text = "Code - ".concat(responseJSON.getCode());
        tv_alert_code.setText(text);
        tv_alert_code.setTypeface(MachinatoFont.getSemiBoldItalic(manage));

        Button b_alert_dialog = (Button) loginDialog.findViewById(R.id.b_alert_dialog);
        b_alert_dialog.setTypeface(MachinatoFont.getSemiBoldItalic(manage));
        b_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!responseJSON.getCode().equals("Error")) {
                    alertDialog.cancel();
                    activity.finish();
                } else {
                    alertDialog.cancel();
                }
            }
        });
        alertDialog.show();
    }

}