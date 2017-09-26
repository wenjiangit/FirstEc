package com.douliu.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import com.douliu.latte.ec.R;
import com.mac.latte.core.delegate.LatteDelegate;

/**
 * Description:${DESC}
 * Author:douliu
 * Created on 2017/9/25.
 */

public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create() {
        Bundle args = new Bundle();
        GoodsDetailDelegate fragment = new GoodsDetailDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
