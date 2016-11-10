package com.apppartner.androidprogrammertest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.apppartner.androidprogrammertest.adapters.ChatsArrayAdapter;
import com.apppartner.androidprogrammertest.controllers.CustomToolbarSvc;
import com.apppartner.androidprogrammertest.models.ChatData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

        /*
        -------------------------- IMPORTANT MESSAGE -----------------------------
        Please view the developer notes, section 2 for important observations
        ----------------------------------------------------------------------
        */

public class ChatActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ActionBarActivity";
    ListView listView;
    private ChatsArrayAdapter chatsArrayAdapter;
    private ProgressBar pb_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        CustomToolbarSvc toolbar = new CustomToolbarSvc(this, "Chat");
        toolbar.activateToolbar();

        listView = (ListView) findViewById(R.id.listView);
        pb_chat = (ProgressBar) findViewById(R.id.pb_chat);

        ArrayList<ChatData> chatDataArrayList = new ArrayList<>();

        try {
            String chatFileData = loadChatFile();
            JSONObject jsonData = new JSONObject(chatFileData);
            JSONArray jsonArray = jsonData.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ChatData chatData = new ChatData(jsonObject);
                chatDataArrayList.add(chatData);
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, e);
        }

        chatsArrayAdapter = new ChatsArrayAdapter(this, chatDataArrayList);
        listView.setAdapter(chatsArrayAdapter);

        listView.setVisibility(View.VISIBLE);
        pb_chat.setVisibility(View.VISIBLE);

        setVisibilityListView();
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    public void setVisibilityListView() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                listView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                pb_chat.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatsArrayAdapter.imgsvc.evictAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatsArrayAdapter.clear();
        chatsArrayAdapter.imgsvc.evictAll();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String loadChatFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.chat_data);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
            stringBuilder.append(receiveString);
            stringBuilder.append("\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        return stringBuilder.toString();
    }

    public void onBackButtonPressed(View v) {
        finish();
    }

}
