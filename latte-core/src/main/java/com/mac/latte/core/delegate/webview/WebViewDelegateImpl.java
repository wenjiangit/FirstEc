package com.mac.latte.core.delegate.webview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mac.latte.core.R;
import com.mac.latte.core.delegate.webview.client.ChromeClient;
import com.mac.latte.core.delegate.webview.client.LatteWebViewClient;
import com.mac.latte.core.delegate.webview.router.Router;
import com.mac.latte.core.utils.Loger;

/**
 * Description:WebViewDelegateImpl
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class WebViewDelegateImpl extends WebViewDelegate
        implements WebViewInitializer,IChrome {

    @Override
    protected WebViewInitializer setInitializer() {
        return this;
    }

    @Override
    protected Object setLayout() {
        return getWebView();
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            Router.loadPage(getWebView(), getUrl());
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initWebView(WebView webView) {
        //允许调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        //cookie相关配置
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.acceptThirdPartyCookies(webView);
        }
//        CookieManager.setAcceptFileSchemeCookies(true);

        //没有水平滚动条
        webView.setHorizontalScrollBarEnabled(false);
        //没有纵向滚动条
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setLongClickable(false);

        WebSettings settings = webView.getSettings();
        //支持JavaScript调用
        settings.setJavaScriptEnabled(true);
        //设置UserAgent信息
      /*  String agentString = settings.getUserAgentString();
        settings.setUserAgentString(agentString + "Latte");*/
        //屏蔽缩放按钮
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //不支持缩放
        settings.setSupportZoom(false);
        //文件访问权限
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }

        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        //允许混合加载,即同时支持http和https
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new ChromeClient(this);
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new LatteWebViewClient(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        View root = getView();
        if (root == null) return;
        TextView txtTitle = (TextView) root.findViewById(R.id.txt_title);
        if (txtTitle != null) {
            txtTitle.setText(title);
        }
    }

    @Override
    public void setProgress(int progress) {
        Loger.d("progress: ", progress);
    }
}
