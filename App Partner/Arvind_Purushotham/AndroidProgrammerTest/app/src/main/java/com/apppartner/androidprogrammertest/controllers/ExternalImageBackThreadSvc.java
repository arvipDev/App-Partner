package com.apppartner.androidprogrammertest.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;

import com.apppartner.androidprogrammertest.adapters.ChatsArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalImageBackThreadSvc extends AsyncTask<String, Void, Bitmap> {
    private ChatsArrayAdapter.ChatCell chat = null;
    private String url = null;

    public ExternalImageBackThreadSvc(ChatsArrayAdapter.ChatCell chat) {
        this.chat = chat;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        Bitmap img = convertURLtoBitmap(url);
        return cropCircularImage(img);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        chat.profileImage.setImageBitmap(bitmap);

        if (chat.cache.get(url) == null) {
            chat.cache.put(url, bitmap);
        }
    }

    private Bitmap convertURLtoBitmap(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.e(" ExternalThreadSvc ", " convertURLtoBitmap ");
            return null;
        }
    }

    private Bitmap cropCircularImage(Bitmap bitmap) {

        Bitmap cropped = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas drawingSheet = new Canvas(cropped);

        final int color = 0xffffffff;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);

        drawingSheet.drawARGB(0, 0, 0, 0);
        drawingSheet.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        drawingSheet.drawBitmap(bitmap, rect, rect, paint);

        return cropped;
    }
}
