package com.mac.latte.core.mvp;

import com.mac.latte.core.mvp.base.MvpPresenter;
import com.mac.latte.core.mvp.base.MvpView;

/**
 *
 * Created by douliu on 2017/8/31.
 */

public class MvpCallbackProxy<V extends MvpView,P extends MvpPresenter<V>> implements MvpCallback<V,P>{

    private MvpCallback<V, P> mCallback;

    public MvpCallbackProxy(MvpCallback<V, P> mvpCallback) {
        mCallback = mvpCallback;
    }

    @Override
    public void attachView(V view) {
        getPresenter().attachView(view);
    }

    @Override
    public void detachView() {
        getPresenter().detachView();
    }

    @Override
    public P createPresenter() {
        P presenter = mCallback.getPresenter();
        if (presenter == null) {
            presenter = mCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter is null");
        }
        mCallback.setPresenter(presenter);
        return presenter;
    }

    @Override
    public P getPresenter() {
        P presenter = mCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("presenter is null");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mCallback.setPresenter(presenter);
    }

    @Override
    public V createView() {
        V view = mCallback.getMvpView();
        if (view == null) {
            view = mCallback.createView();
        }
        if (view == null) {
            throw new NullPointerException("view is null");
        }
        mCallback.setMvpView(view);
        return view;
    }

    @Override
    public V getMvpView() {
        V view = mCallback.getMvpView();
        if (view == null) {
            throw new NullPointerException("view is null");
        }
        return view;
    }

    @Override
    public void setMvpView(V view) {
        mCallback.setMvpView(view);
    }
}
