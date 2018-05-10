package com.m.emad.madarsoft.usecase;

import com.m.emad.madarsoft.error.ErrorHandler;
import com.m.emad.madarsoft.error.ErrorModel;

import io.reactivex.observers.DisposableObserver;


public abstract class UseCaseObserver<T> extends DisposableObserver<T> {


    @Override
    public void onError(Throwable e) {
        OnFailed(ErrorHandler.getErrorModel(e));
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
    public abstract void OnFailed(ErrorModel errorModel);

    @Override
    public void onComplete() {

    }
}
