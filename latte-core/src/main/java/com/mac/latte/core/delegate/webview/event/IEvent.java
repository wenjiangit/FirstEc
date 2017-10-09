package com.mac.latte.core.delegate.webview.event;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public interface IEvent {

    String ACTION_TEST = "test";

    String ACTION_UNDEFINE = "undefine";

    void execute(String params);

}
