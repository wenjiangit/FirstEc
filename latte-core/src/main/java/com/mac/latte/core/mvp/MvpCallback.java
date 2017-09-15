package com.mac.latte.core.mvp;

import com.mac.latte.core.mvp.base.MvpPresenter;

/**
 * Created by douliu on 2017/8/31.
 */

public interface MvpCallback<V,P> extends MvpPresenter<V>{

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V createView();

    V getMvpView();

    void setMvpView(V view);

}
