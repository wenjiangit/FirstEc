package com.mac.firstec;

import android.os.Bundle;
import android.view.View;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.IError;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
