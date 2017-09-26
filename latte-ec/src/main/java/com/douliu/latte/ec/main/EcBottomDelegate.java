package com.douliu.latte.ec.main;

import android.graphics.Color;
import android.os.Bundle;

import com.douliu.latte.ec.main.home.HomeDelegate;
import com.douliu.latte.ec.main.sort.SortDelegate;
import com.mac.latte.core.delegate.bottom.BaseBottomDelegate;
import com.mac.latte.core.delegate.bottom.BottomItemBean;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;
import com.mac.latte.core.delegate.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 *
 * Created by douliu on 2017/9/21.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    public static EcBottomDelegate newInstance() {
        Bundle args = new Bundle();
        EcBottomDelegate fragment = new EcBottomDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setClickColor() {
        return Color.parseColor("#ffffbb33");
    }

    @Override
    protected int setDelegateIndex() {
        return 0;
    }

    @Override
    protected LinkedHashMap<BottomItemBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        return builder.addItem(new BottomItemBean("{fa-home}", "首页"), HomeDelegate.newInstance())
                .addItem(new BottomItemBean("{fa-sort}", "分类"), SortDelegate.create())
                .addItem(new BottomItemBean("{fa-compass}", "发现"), new IndexDelegate())
                .addItem(new BottomItemBean("{fa-shopping-cart}", "购物车"), new IndexDelegate())
                .addItem(new BottomItemBean("{fa-user}", "我的"), new IndexDelegate())
                .build();
    }



}
