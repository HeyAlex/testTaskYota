package com.testproject1.alexey.yotatest.callback;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public interface MainCallback {
    void validateUrl(String url);
    void getData(String url);
    void OnErrorToGetData(String error);
    void OnSuccesesToGetData(String data);
    void cancelTask();
}
