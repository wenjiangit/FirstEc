package com.mac.firstec;

import com.douliu.latte.ec.launcher.ILauncherListener;
import com.douliu.latte.ec.launcher.LauncherDelegate;
import com.douliu.latte.ec.launcher.LauncherTag;
import com.douliu.latte.ec.sign.ISignListener;
import com.douliu.latte.ec.sign.SignInDelegate;
import com.mac.latte.core.activities.ProxyActivity;
import com.mac.latte.core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity
        implements ISignListener, ILauncherListener {

    @Override
    protected LatteDelegate setRootDelegate() {
        return LauncherDelegate.newInstance();
    }

    @Override
    public void onSignInSuccess() {
        toMainDelegate();
    }

    @Override
    public void onSignUpSuccess() {
        toMainDelegate();
    }

    @Override
    public void onLauncherFinished(LauncherTag tag) {
        switch (tag) {
            case SIGN:
                toMainDelegate();
                break;
            case NOT_SIGN:
                getSupportDelegate().startWithPop(SignInDelegate.newInstance());
                break;
            default:
                break;
        }
    }

    private void toMainDelegate() {
        getSupportDelegate().startWithPop(new ExampleDelegate());
    }
}
