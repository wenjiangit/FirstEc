package com.mac.latte.core.delegate.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.delegate.webview.router.RouterKeys;

/**
 * Description:WebViewDelegate
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public abstract class WebViewDelegate extends LatteDelegate{

    private WebView mWebView;

    private boolean mIsWebViewAvailable;
    private String mUrl;
    private LatteDelegate mTopDelegate;

    protected abstract WebViewInitializer setInitializer();

    public static WebViewDelegate create(String url) {
        Bundle args = new Bundle();
        args.putString(RouterKeys.URL.name(), url);
        WebViewDelegate fragment = new WebViewDelegateImpl();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mUrl = arguments.getString(RouterKeys.URL.name());
        init();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void init() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            WebViewInitializer initializer = setInitializer();
            if (initializer == null) {
                throw new NullPointerException("WebViewInitializer must not be null !");
            }
            mWebView = new WebView(getContext());
            initializer.initWebView(mWebView);
            mWebView.setWebChromeClient(initializer.initWebChromeClient());
            mWebView.setWebViewClient(initializer.initWebViewClient());
            mWebView.addJavascriptInterface(LatteWebInterface.create(this), "Latte");
            mIsWebViewAvailable = true;
        }
    }

    public void setTopDelegate(LatteDelegate delegate) {
        this.mTopDelegate = delegate;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        return mUrl;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            return this;
        }
        return mTopDelegate;
    }
}
