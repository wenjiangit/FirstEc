package com.mac.latte.core.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;

import com.mac.latte.core.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by douliu on 2017/9/15.
 */

public abstract class BaseBottomDelegate extends LatteDelegate{

    private final List<BottomItemBean> mItemBeans = new ArrayList<>();
    private final List<BottomItemDelegate> mItemDelegates = new ArrayList<>();
    private final LinkedHashMap<BottomItemBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private BottomItemDelegate mCurrentDelegate;
    private int mCurrentIndex = 0;
    @ColorRes
    private int mClickColor = Color.RED;

    @ColorRes
    protected abstract int setClickColor();

    protected abstract BottomItemDelegate setIndexDelegate();

    protected abstract LinkedHashMap<BottomItemBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentDelegate = setIndexDelegate();
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }

        ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomItemBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomItemBean, BottomItemDelegate> entry : ITEMS.entrySet()) {
            BottomItemBean key = entry.getKey();
            BottomItemDelegate value = entry.getValue();
            mItemBeans.add(key);
            mItemDelegates.add(value);
        }

    }
}
