package com.testproject1.alexey.yotatest.presenter;

import com.testproject1.alexey.yotatest.callback.IMainView;
import com.testproject1.alexey.yotatest.callback.MainCallback;
import com.testproject1.alexey.yotatest.interactor.MainInteractor;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public class MainPresenter extends BasePresenter implements MainCallback{

    private IMainView mCallback;
    public MainPresenter(IMainView mCallback) {
        mInteractor = new MainInteractor(this);
        this.mCallback = mCallback;
    }

    @Override
    public void validateUrl() {

    }

    @Override
    public void getData(String url) {
        mInteractor.startDownloading(url);
    }

    @Override
    public void OnErrorToGetData() {
        mCallback.OnEmptyData();
    }

    @Override
    public void OnSuccesesToGetData(String data) {
        mCallback.DisplayResult(data);
    }

    @Override
    public void cancelTask() {
        mInteractor.destroy();
    }

    @Override
    public void destroy() {
        mInteractor.destroy();
    }
}
