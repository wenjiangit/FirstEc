package com.mac.latte.core.wechat.templates;

import com.mac.latte.core.wechat.BaseWxEntryActivity;
import com.mac.latte.core.wechat.LatteWechat;

/**
 *
 * Created by douliu on 2017/8/21.
 */

public class WXEntryTemplate extends BaseWxEntryActivity {

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWechat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
