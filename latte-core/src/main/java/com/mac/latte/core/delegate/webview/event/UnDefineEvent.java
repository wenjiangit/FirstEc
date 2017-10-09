package com.mac.latte.core.delegate.webview.event;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/28.
 */

public class UnDefineEvent extends Event{

    @Override
    public void execute(String params) {

    }

    @Override
    public String getAction() {
        return IEvent.ACTION_UNDEFINE;
    }

}
