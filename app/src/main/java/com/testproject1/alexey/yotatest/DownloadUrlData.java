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
    private String web_page;

    public DownloadUrlData(BaseInteractor listener, String url) {
        this.curentURL = url;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        curentURL = "";
        web_page = "";
    }

    @Override
    protected String doInBackground(Void... voids) {
        URL url = null;
        try {
            url = new URL(curentURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            // Receiving data through the input stream
            InputStream is = conn.getInputStream();
            web_page = readStringFromInputStream(is);
            is.close();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
        }


      return null;
    }

    private static String readStringFromInputStream(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while ((line = reader.readLine()) != null) {
            total.append(line);
        }

        line = total.toString();
        return line;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(web_page.isEmpty()) listener.OnError();
        else listener.OnComplete(web_page);
    }
}
