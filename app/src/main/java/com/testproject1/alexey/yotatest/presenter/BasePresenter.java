package com.testproject1.alexey.yotatest.presenter;

import com.testproject1.alexey.yotatest.interactor.MainInteractor;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public abstract class BasePresenter {
    protected MainInteractor mInteractor;
    public abstract void destroy();
}
