package com.m.emad.madarsoft.base;

public interface BaseContract {

    interface BaseView {

    }

    interface View<T extends BasePresenter> extends BaseView{
        void setPresenter(T presenter);
        void showLoading();
        void showContent();
    }

    interface BasePresenter<T extends BaseView> {
        void attachView(T view);
        T getView();
        void detachView();
        void cancelAllRequests();
    }
}
