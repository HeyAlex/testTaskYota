package com.testproject1.alexey.yotatest.presenter;

import com.testproject1.alexey.yotatest.UtilClass;
import com.testproject1.alexey.yotatest.callback.IMainView;
import com.testproject1.alexey.yotatest.callback.MainCallback;
import com.testproject1.alexey.yotatest.interactor.MainInteractor;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public class MainPresenter implements MainCallback{

    private MainInteractor mInteractor;
    private IMainView mCallback;
    public MainPresenter(IMainView mCallback) {
        mInteractor = new MainInteractor(this);
        this.mCallback = mCallback;
    }

    @Override
    public void validateUrl(String url) {
        if(UtilClass.urlValidation(url)){
          mCallback.enableStateButton(true);
            mCallback.OnError("");
        }else {
            if(UtilClass.mainValidation(url)) mCallback.OnError("URL should be correct");
            else mCallback.OnError("Start URL with \"http://\" ");

            mCallback.enableStateButton(false);
        }
    }

    @Override
    public void getData(String url) {
        mInteractor.startDownloading(url);
    }

    @Override
    public void OnErrorToGetData(String error) {
        mCallback.OnError(error);
    }

    @Override
    public void OnSuccesesToGetData(String data) {
        mCallback.DisplayResult(data);
        mCallback.enableState(true);
    }

    @Override
    public void cancelTask() {
        mInteractor.destroy();
        mCallback.enableState(true);
    }


    public void destroy() {
        mInteractor.destroy();
    }
}
