package com.douliu.latte.ec.launcher;

import android.app.Activity;

import com.douliu.latte.ec.account.AccountManager;
import com.douliu.latte.ec.account.IUserChecker;
import com.douliu.latte.ec.main.EcBottomDelegate;
import com.douliu.latte.ec.sign.SignInDelegate;
import com.mac.latte.core.delegate.LatteDelegate;

/**
 * 启动页面的基类
 * Created by douliu on 2017/8/14.
 */

public abstract class AbsLauncherDelegate extends LatteDelegate {

    protected ILauncherListener mLauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    protected void checkSign() {
        AccountManager.checkAccount(new IUserChecker() {
            @Override
            public void onSignIn() {
                startWithPop(EcBottomDelegate.newInstance());
               /* if (mLauncherListener != null) {
                    mLauncherListener.onLauncherFinished(LauncherTag.SIGN);
                }*/
            }

            @Override
            public void onNotSignIn() {
                startWithPop(SignInDelegate.newInstance());
               /* if (mLauncherListener != null) {
                    mLauncherListener.onLauncherFinished(LauncherTag.NOT_SIGN);
                }*/
            }
        });
    }
}
