package com.codefooign.listapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pullapi.Article;
import pullapi.IGNAPI;
import pullapi.Video;

public class MainActivity extends Activity {

    private static final int START_INDEX = 0;
    private static final int PULL_SIZE = 5;
    private Button mBtnVideos;
    private Button mBtnArticles;
    private WebView mWebView;
    private ListView mListView;
    private ListArticle listArticles[];
    private Article[] articles;
    private Video[] videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listArticles = new ListArticle[PULL_SIZE];

        final PullTask task = new PullTask();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
        }

        mBtnVideos = (Button) findViewById(R.id.btn_getVideos);
        mBtnVideos.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBtnArticles.setVisibility(View.INVISIBLE);
                mBtnVideos.setVisibility(View.INVISIBLE);
                task.execute(IGNAPI.VIDEOS);
                Toast.makeText(MainActivity.this, "Getting information", Toast.LENGTH_LONG).show();

            }
        });

        mBtnArticles = (Button) findViewById(R.id.btn_getArticles);

        mBtnArticles.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBtnVideos.setVisibility(View.INVISIBLE);
                mBtnArticles.setVisibility(View.INVISIBLE);
                task.execute(IGNAPI.ARTICLES);
                Toast.makeText(MainActivity.this, "Getting information", Toast.LENGTH_LONG).show();

            }
        });

    }

    private class PullTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            Date currentTime = new Date();

            if (params[0] == IGNAPI.ARTICLES) {
                articles = IGNAPI.pullArticles(START_INDEX, PULL_SIZE);

                for (int index = 0; index < PULL_SIZE; index++) {

                    Article currentArticle = articles[index];
                    String timeUnit = getTimePassed(currentTime, currentArticle);

                    listArticles[index] = new ListArticle(
                            currentArticle.getThumbnail(),
                            currentArticle.getHeadline(),
                            timeUnit,
                            currentArticle.getUrl());
                }

            } else if (params[0] == IGNAPI.VIDEOS) {
                videos = IGNAPI.pullVideos(START_INDEX, PULL_SIZE);

                for (int index = 0; index < PULL_SIZE; index++) {

                    Video currentVideo = videos[index];
                    String timeUnit = getTimePassed(currentTime, currentVideo);

                    listArticles[index] = new ListArticle(
                            currentVideo.getThumbnail(),
                            currentVideo.getTitle(),
                            timeUnit,
                            currentVideo.getUrl());
                }
            }

            return null;
        }

        @NonNull
        private String getTimePassed(Date currentTime, Article currentArticle) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
            Date pubDate;
            String timeUnit;
            long timePassed, outputTime;
            long hours = 0, seconds = 0, minutes = 0;

            try {
                pubDate = df.parse(currentArticle.getPublishDate());

                timePassed = currentTime.getTime() - pubDate.getTime();
                seconds = timePassed / 1000 % 60;
                minutes = timePassed / (60 * 1000);
                hours = minutes / 60;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (hours >= 24) {
                outputTime = hours / 24;
                timeUnit = outputTime + " days ago";
            } else if (minutes >= 60) {
                outputTime = minutes / 60;
                timeUnit = outputTime + " hours ago";
            } else if (seconds >= 60) {
                outputTime = seconds / 60;
                timeUnit = outputTime + " minutes ago";
            } else {
                timeUnit = "not received";
            }
            return timeUnit;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ListArticleAdapter adapter = new ListArticleAdapter(
                    MainActivity.this, R.layout.lv_item_row, listArticles);

            mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ListArticle currentArticle = (ListArticle) mListView.getItemAtPosition(position);

                    mWebView = new WebView(MainActivity.this);
                    mWebView.loadUrl(currentArticle.url);
                }
            });
        }

    }

}

