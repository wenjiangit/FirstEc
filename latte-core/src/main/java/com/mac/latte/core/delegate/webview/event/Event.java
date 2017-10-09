package com.mac.latte.core.delegate.webview.event;

import android.content.Context;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public abstract class Event implements IEvent {
    private Context context;
    private String url;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public abstract String getAction();

}
