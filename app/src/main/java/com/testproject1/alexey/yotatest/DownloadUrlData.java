package com.testproject1.alexey.yotatest;

import android.os.AsyncTask;

import com.testproject1.alexey.yotatest.interactor.BaseInteractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ALEXEY on 10/18/2016.
 */

public class DownloadUrlData extends AsyncTask<Void, Integer, String> {

    private String curentURL;
    private BaseInteractor listener;
    private String source;

    public DownloadUrlData(BaseInteractor listener, String url) {
        this.curentURL = url;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // curentURL = "";
        source = "";
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            source = UtilClass.downloadURL(curentURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(source.isEmpty()) listener.OnError();
        else listener.OnComplete(source);
    }
}
