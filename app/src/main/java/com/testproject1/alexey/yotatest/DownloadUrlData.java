package com.testproject1.alexey.yotatest;

import android.os.AsyncTask;

import com.testproject1.alexey.yotatest.interactor.BaseInteractor;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

/**
 * Created by ALEXEY on 10/18/2016.
 */

public class DownloadUrlData extends AsyncTask<Void, Integer, String> {

    private String curentURL;
    private BaseInteractor listener;
    private String source;
    private String error;

    public DownloadUrlData(BaseInteractor listener, String url) {
        this.curentURL = url;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        source = "";
        error = "";
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            source = UtilClass.downloadURL(curentURL);
            if(source.isEmpty()) error = "Nothing on current URL";
        }
        catch (FileNotFoundException ex) {
            error = "Host exist, but current url is not found";
        }
        catch(UnknownHostException ex) {
            error = "Host doesn't exist";
        }
        catch (Exception ex) {
            error = "Something gonna wrong";
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(source.isEmpty()) listener.OnError(error);
        else listener.OnComplete(source);
    }
}
