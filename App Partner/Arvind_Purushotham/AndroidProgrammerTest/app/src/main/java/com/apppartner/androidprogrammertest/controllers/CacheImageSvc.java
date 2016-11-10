package com.apppartner.androidprogrammertest.controllers;

import android.graphics.Bitmap;
import android.util.LruCache;

public class CacheImageSvc extends LruCache<String, Bitmap> {
    public CacheImageSvc(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value == null ? 0 : value.getRowBytes() * value.getHeight() / 1024;
    }
}
