package com.douliu.latte.ec.main.home;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.douliu.latte.ec.detail.GoodsDetailDelegate;
import com.mac.latte.core.delegate.LatteDelegate;

/**
 * Description:HomeItemClickListener
 * Author:douliu
 * Created on 2017/9/25.
 */

public class HomeItemClickListener extends SimpleClickListener{

    private LatteDelegate mParentDelegate;

    HomeItemClickListener(LatteDelegate parentDelegate) {
        mParentDelegate = parentDelegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        mParentDelegate.start(GoodsDetailDelegate.create());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
