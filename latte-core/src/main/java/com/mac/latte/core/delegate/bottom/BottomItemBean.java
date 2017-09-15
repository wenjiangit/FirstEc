package com.mac.latte.core.delegate.bottom;

/**
 * tab实体bean
 * Created by douliu on 2017/9/15.
 */

public class BottomItemBean {

    private final String icon;

    private final String title;

    public BottomItemBean(String icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
