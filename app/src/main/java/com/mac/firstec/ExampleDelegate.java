package com.mac.firstec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.rx.RxRestClient;
import com.mac.latte.core.ui.LatteLoader;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by mac on 2017/8/6.
 */

public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";

    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R.id.test)
    public void onClick() {
        RxRestClient.buider()
                .url("https://www.baidu.com")
                .loader(getContext()).build().get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        LatteLoader.stopLoading();
                    }
                });

    }

}
