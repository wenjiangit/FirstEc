package com.mac.latte.core.delegate.webview.client;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mac.latte.core.delegate.webview.WebViewDelegate;
import com.mac.latte.core.delegate.webview.router.Router;
import com.mac.latte.core.ui.LatteLoader;
import com.mac.latte.core.utils.LattePreferences;
import com.mac.latte.core.utils.Loger;

/**
 * Description:WebViewClient
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class LatteWebViewClient extends WebViewClient {

    private final WebViewDelegate mDelegate;

    public LatteWebViewClient(WebViewDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.shouldOverrideUrlLoading(mDelegate, url);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//        super.onReceivedSslError(view, handler, error);
        //等待证书认证
        handler.proceed();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie(url);
        LatteLoader.stopLoading();
    }

    private void syncCookie(String url) {
        CookieManager manager = CookieManager.getInstance();
        String cookie = manager.getCookie(url);
        if (!TextUtils.isEmpty(cookie)) {
            Loger.d("cookie", cookie);
            LattePreferences.setCustomProfile("cookie", cookie);
        }
    }
}
