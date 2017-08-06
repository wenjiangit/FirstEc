package com.mac.firstec;

import android.os.Bundle;
import android.view.View;

import com.mac.latte.core.delegate.LatteDelegate;

/**
 *
 * Created by mac on 2017/8/6.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
