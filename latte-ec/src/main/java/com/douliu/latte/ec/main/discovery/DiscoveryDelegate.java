package com.douliu.latte.ec.main.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.douliu.latte.ec.R;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;
import com.mac.latte.core.delegate.webview.WebViewDelegate;

/**
 * Description:发现页面
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class DiscoveryDelegate extends BottomItemDelegate{

    public static DiscoveryDelegate create() {
        Bundle args = new Bundle();
        DiscoveryDelegate fragment = new DiscoveryDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_discovery;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        WebViewDelegate delegate = WebViewDelegate.create("https://www.baidu.com");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_container, delegate);
    }
}
