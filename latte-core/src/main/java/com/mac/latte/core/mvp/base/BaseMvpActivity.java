package com.mac.latte.core.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mac.latte.core.mvp.ActivityMvpDelegate;
import com.mac.latte.core.mvp.ActivityMvpDelegateImpl;
import com.mac.latte.core.mvp.MvpCallback;

/**
 *
 * Created by douliu on 2017/8/31.
 */

public abstract class BaseMvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends AppCompatActivity
        implements MvpCallback<V,P>{

    private ActivityMvpDelegate mMvpDelegate;
    private P presenter;
    private V view;

    public ActivityMvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new ActivityMvpDelegateImpl<>(this);
        }
        return mMvpDelegate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
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
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return this.view;
    }

    @Override
    public void setMvpView(V view) {
        this.view = view;
    }
}
