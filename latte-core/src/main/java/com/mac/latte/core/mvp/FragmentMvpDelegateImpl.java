package com.mac.latte.core.mvp;

import android.os.Bundle;
import android.view.View;

import com.mac.latte.core.mvp.base.MvpPresenter;
import com.mac.latte.core.mvp.base.MvpView;

/**
 * Created by douliu on 2017/8/31.
 */

public class FragmentMvpDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements FragmentMvpDelegate{

    private MvpCallback<V, P> callback;
    private MvpCallbackProxy<V, P> callbackProxy;

    public FragmentMvpDelegateImpl(MvpCallback<V, P> callback) {
        this.callback = callback;
    }

    public MvpCallbackProxy<V, P> getCallbackProxy() {
        if (callbackProxy == null) {
            callbackProxy = new MvpCallbackProxy<>(callback);
        }
        return callbackProxy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getCallbackProxy().createView();
        getCallbackProxy().createPresenter();
        getCallbackProxy().attachView(getCallbackProxy().getMvpView());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {
        getCallbackProxy().detachView();
    }
}
