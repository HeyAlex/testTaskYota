package com.testproject1.alexey.yotatest.callback;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public interface IMainView {
    void downloadData();

    void OnBadUrl();

    void OnEmptyData();

    void DisplayResult(String data);

    void InteruptDownloading();
}