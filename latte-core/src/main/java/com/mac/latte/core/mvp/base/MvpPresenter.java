package com.mac.latte.core.mvp.base;

/**
 * Created by douliu on 2017/8/31.
 */

public interface MvpPresenter<V> {

    void attachView(V view);

    void detachView();

}
