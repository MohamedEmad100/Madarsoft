package com.m.emad.madarsoft.usecase;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public abstract class UseCase<T> {
    private CompositeDisposable mCompositeDisposable;
    private Scheduler executor;
    private Scheduler ui;
    private static final int LOADING_PATCH_THRESHOLD = 12;

    public UseCase(Scheduler executor, Scheduler ui) {
        this.executor = executor;
        this.ui = ui;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> disposableObserver , Map<String,Object> map){
        if (disposableObserver == null){
            throw new IllegalArgumentException("Observer must be null");
        }

        final Observable<T> observable = createObservableUseCase(map).observeOn(executor).observeOn(ui);
        DisposableObserver<T> observer = observable.subscribeWith(disposableObserver);
        mCompositeDisposable.add(observer);
    }

    public void clear(){mCompositeDisposable.clear();}
    protected abstract Observable<T> createObservableUseCase(Map<String,Object> map);
    public int getLoadingPatchThreshold(){
        return LOADING_PATCH_THRESHOLD;
    }

}
