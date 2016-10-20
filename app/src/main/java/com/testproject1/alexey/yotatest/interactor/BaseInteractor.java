package com.testproject1.alexey.yotatest.interactor;

/**
 * Created by ALEXEY on 10/16/2016.
 */

public abstract class BaseInteractor {
    public abstract void OnComplete(String text);
    public abstract void OnError(String error);
    public abstract void destroy();
}
