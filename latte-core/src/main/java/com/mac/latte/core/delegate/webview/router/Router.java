package com.mac.latte.core.delegate.webview.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.delegate.webview.WebViewDelegate;

/**
 * Description:页面跳转路由
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class Router {

    private static final String ASSET_BASE = "file:///android_asset/";

    private Router() {
    }

    public static boolean shouldOverrideUrlLoading(WebViewDelegate delegate, String url) {
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        if (!URLUtil.isNetworkUrl(url)) {
            openExternalApp(delegate.getContext(), url);
            return true;
        }

        LatteDelegate parentDelegate = delegate.getTopDelegate();
        WebViewDelegate webViewDelegate = WebViewDelegate.create(url);
        parentDelegate.start(webViewDelegate);

        return true;
    }

    /**
     * 开启第三方应用
     *
     * @param context Context
     * @param url     如weixin://adjafafa,集成爱贝支付时遇到过
     */
    private static void openExternalApp(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            ContextCompat.startActivity(context, intent, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 打电话
     *
     * @param context Context
     * @param url     电话号码的url
     */
    private static void callPhone(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(url));
        ContextCompat.startActivity(context, intent, null);
    }


    /**
     * 加载网络页面
     *
     * @param webView WebView
     * @param url     网络页面地址
     */
    private static void loadWebPage(WebView webView, String url) {
        webView.loadUrl(url);
    }

    /**
     * 加载本地Assert目录下的页面
     *
     * @param webView WebView
     * @param url     本地资源名
     */
    private static void loadLocalPage(WebView webView, String url) {
        webView.loadUrl(ASSET_BASE + url);
    }

    public static void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

}
