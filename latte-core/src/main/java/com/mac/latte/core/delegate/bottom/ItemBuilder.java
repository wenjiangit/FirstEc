package com.mac.latte.core.delegate.bottom;

import java.util.LinkedHashMap;

/**
 *
 * 主页面几个tab的构造器
 * Created by douliu on 2017/9/15.
 */

public class ItemBuilder {

    private final LinkedHashMap<BottomItemBean, BottomItemDelegate> BOTTOM_ITEMS = new LinkedHashMap<>();

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomItemBean bean, BottomItemDelegate delegate) {
        BOTTOM_ITEMS.put(bean, delegate);
        return this;
    }


    public ItemBuilder addItems(LinkedHashMap<BottomItemBean, BottomItemDelegate> items) {
        BOTTOM_ITEMS.putAll(items);
        return this;
    }

    public LinkedHashMap<BottomItemBean, BottomItemDelegate> build(){
        return BOTTOM_ITEMS;
    }

}
