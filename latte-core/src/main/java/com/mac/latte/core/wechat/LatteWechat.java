package com.mac.latte.core.wechat;

import android.app.Activity;

import com.mac.latte.core.app.ConfigKey;
import com.mac.latte.core.app.Latte;
import com.mac.latte.core.wechat.callback.IWechatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 *
 *
 * Created by douliu on 2017/8/28.
 */

public class LatteWechat {

    private static final String APP_ID = Latte.getConfiguration(ConfigKey.WX_APP_ID);
    private static final String APP_SECRET = Latte.getConfiguration(ConfigKey.WX_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWechatSignInCallback mSignInCallback;

    public static LatteWechat getInstance(){
        return Holder.INSTANCE;
    }

    private LatteWechat(){
        Activity activity = Latte.getConfiguration(ConfigKey.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    private static class Holder{
        private static final LatteWechat INSTANCE = new LatteWechat();
    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWechat onSignInSuccess(IWechatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWechatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public void signIn(){
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        WXAPI.sendReq(req);
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getAppSecret() {
        return APP_SECRET;
    }
}
