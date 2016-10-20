package com.testproject1.alexey.yotatest.interactor;

import com.testproject1.alexey.yotatest.DownloadUrlData;
import com.testproject1.alexey.yotatest.callback.MainCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public class MainInteractor extends BaseInteractor{

    private MainCallback mCallback;
    private DownloadUrlData mTask;
    private String web_page;
    public MainInteractor(MainCallback mainCallback) {
        this.mCallback = mainCallback;
    }


    public void startDownloading(String url){
        mTask = new DownloadUrlData(this,url);
        mTask.execute();
    }



    @Override
    public void OnComplete(String text) {
        mCallback.OnSuccesesToGetData(text);
    }

    @Override
    public void OnError(String error) {
        mCallback.OnErrorToGetData(error);
    }

    @Override
    public void destroy() {
        mTask.cancel(true);
    }
}
