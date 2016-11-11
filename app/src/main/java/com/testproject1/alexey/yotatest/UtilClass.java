package com.testproject1.alexey.yotatest;

import android.util.Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ALEXEY on 10/19/2016.
 */

public class UtilClass {

    public static String downloadURL(String current_url) throws IOException {
        URL url = new URL(current_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        InputStream is = conn.getInputStream();
        String source  = readeFromStream(is);
        is.close();
        return source;
    }

    private static String readeFromStream(InputStream is) throws IOException {
        String line;
        StringBuilder total = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while ((line = reader.readLine()) != null) {
            total.append(line);
        }

        line = total.toString();
        return line;
    }

    public static boolean mainValidation(String url) {
        return url.contains("http://");
    }

    public static boolean urlValidation(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

}
