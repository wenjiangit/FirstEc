package com.mac.latte.core.delegate.webview.client;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.mac.latte.core.delegate.webview.IChrome;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class ChromeClient extends WebChromeClient {

    private IChrome mChrome;

    public ChromeClient(IChrome chrome) {
        mChrome = chrome;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        mChrome.setTitle(title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        mChrome.setProgress(newProgress);
    }
}
