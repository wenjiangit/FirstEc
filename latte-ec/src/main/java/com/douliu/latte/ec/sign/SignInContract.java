package com.douliu.latte.ec.sign;

import android.content.Context;

import com.mac.latte.core.mvp.base.MvpPresenter;
import com.mac.latte.core.mvp.base.MvpView;

/**
 *
 * Created by douliu on 2017/8/31.
 */

public interface SignInContract {

    interface View extends MvpView{

        void signInSuccess();

        Context getContext();

        void wxLoginSuccess(String userInfo);

    }

    interface Presenter extends MvpPresenter<View> {

        void signIn();

        void wxLogin();

    }


}
