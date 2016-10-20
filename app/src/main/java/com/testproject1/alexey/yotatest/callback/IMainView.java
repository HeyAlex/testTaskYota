package com.testproject1.alexey.yotatest.callback;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public interface IMainView {

    void DisplayResult(String data);

    void InteruptDownloading();

    void enableState(boolean state);

    void enableStateButton(boolean state);

    void OnError(String hint);
}