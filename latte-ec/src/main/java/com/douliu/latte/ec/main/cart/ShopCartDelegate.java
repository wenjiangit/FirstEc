package com.douliu.latte.ec.main.cart;

import android.os.Bundle;
import android.view.View;

import com.douliu.latte.ec.R;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;

/**
 * Description:购物车
 * Author:douliu
 * Date:Created on 2017/10/10.
 */

public class ShopCartDelegate extends BottomItemDelegate {

    public static ShopCartDelegate create() {
        Bundle args = new Bundle();
        ShopCartDelegate fragment = new ShopCartDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
