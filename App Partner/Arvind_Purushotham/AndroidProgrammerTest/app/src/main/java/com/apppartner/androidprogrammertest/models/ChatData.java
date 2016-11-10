package com.apppartner.androidprogrammertest.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatData implements Parcelable {
    private static final String LOG_TAG = "ChatData";

    private int userID;
    public String username;
    public String avatarURL;
    public String message;

    public ChatData(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                userID = jsonObject.getInt("user_id");
                username = jsonObject.getString("username");
                avatarURL = jsonObject.getString("avatar_url");
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                Log.w(LOG_TAG, e);
            }
        }
    }

    private ChatData(Parcel in) {
        userID = in.readInt();
        username = in.readString();
        avatarURL = in.readString();
        message = in.readString();
    }

    public static final Creator<ChatData> CREATOR = new Creator<ChatData>() {
        @Override
        public ChatData createFromParcel(Parcel in) {
            return new ChatData(in);
        }

        @Override
        public ChatData[] newArray(int size) {
            return new ChatData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(username);
        dest.writeString(avatarURL);
        dest.writeString(message);
    }
}
