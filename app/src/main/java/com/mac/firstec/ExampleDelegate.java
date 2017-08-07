package com.mac.firstec;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;

import butterknife.OnClick;

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
        RestClient.buider()
                .url("https://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "onSuccess: " + response);
                    }
                }).build()
                .get();
    }

}
