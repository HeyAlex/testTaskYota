package com.testproject1.alexey.yotatest.callback;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public interface MainCallback {
    void validateUrl();
    void getData(String url);
    void OnErrorToGetData();
    void OnSuccesesToGetData(String data);
    void cancelTask();
}
