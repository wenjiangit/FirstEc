package com.mac.latte.core.delegate.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Description:WebViewInitializer
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public interface WebViewInitializer {

    void initWebView(WebView webView);

    WebChromeClient initWebChromeClient();

    WebViewClient initWebViewClient();
}
