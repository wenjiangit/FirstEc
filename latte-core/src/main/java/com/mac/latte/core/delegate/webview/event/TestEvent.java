package com.mac.latte.core.delegate.webview.event;

import android.widget.Toast;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/28.
 */

public class TestEvent extends Event{
    @Override
    public void execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getAction() {
        return ACTION_TEST;
    }
}
