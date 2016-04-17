package com.codefooign.listapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

public class ListArticle {

    public String thumbnailUrl;
    public Bitmap thumbnail;
    public String title;
    public String timePosted;
    public String url;

    public ListArticle(String thumbnailUrl, String title, String timePosted, String url) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.timePosted = timePosted;
        this.url = url;
        this.thumbnail = getBitmap(thumbnailUrl);
    }

    private Bitmap getBitmap(String urlStr) {

        URL url = null;
        Bitmap dlBitmap = null;

        try {
            url = new URL(urlStr);
            dlBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dlBitmap;
    }
}
