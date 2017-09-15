package com.douliu.latte.ec.sign;

import com.douliu.latte.ec.api.Api;
import com.mac.latte.core.mvp.base.BaseMvpPresenter;
import com.mac.latte.core.net.RestClient;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.utils.Loger;
import com.mac.latte.core.wechat.LatteWechat;
import com.mac.latte.core.wechat.callback.IWechatSignInCallback;

/**
 *
 *
 * Created by douliu on 2017/8/31.
 */

public class SignInPresenter extends BaseMvpPresenter<SignInContract.View>
        implements SignInContract.Presenter{

    private static final String TAG = "SignInPresenter";

    @Override
    public void signIn() {
        RestClient.buider()
                .url(Api.USER_PROFILE)
                .loader(getView().getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Loger.i(TAG, response);
                        SignHandler.signIn(response);
                        if (getView() != null) {
                            getView().signInSuccess();
                        }
                    }
                }).build()
                .get();
    }

    @Override
    public void wxLogin() {
        LatteWechat.getInstance()
                .onSignInSuccess(new IWechatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        if (getView() != null) {
                            getView().wxLoginSuccess(userInfo);
                        }
                    }
                }).signIn();

    }
}
