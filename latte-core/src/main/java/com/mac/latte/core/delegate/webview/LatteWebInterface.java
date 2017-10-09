package com.mac.latte.core.delegate.webview;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mac.latte.core.delegate.webview.event.Event;
import com.mac.latte.core.delegate.webview.event.EventManager;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class LatteWebInterface {

    private static final String ACTION = "action";

    private final WebViewDelegate mDelegate;

    private LatteWebInterface(WebViewDelegate delegate) {
        mDelegate = delegate;
    }

    static LatteWebInterface create(WebViewDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public void event(String params) {
        String action = JSON.parseObject(params).getString(ACTION);
        if (TextUtils.isEmpty(action)) {
            Toast.makeText(mDelegate.getContext(), "参数有误!", Toast.LENGTH_SHORT).show();
        } else {
            Event event = EventManager.getInstance().createEvent(action);
            event.setContext(mDelegate.getContext());
            event.setUrl(mDelegate.getUrl());
            event.execute(params);
        }
    }
}
