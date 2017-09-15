package com.mac.latte.core.mvp.base;

/**
 * Created by douliu on 2017/8/31.
 */

public class BaseMvpPresenter<V> implements MvpPresenter<V>{

    private V view;

    protected V getView(){
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
