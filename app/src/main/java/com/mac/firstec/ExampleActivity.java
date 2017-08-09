package com.mac.firstec;

import com.douliu.latte.ec.launcher.LauncherDelegate;
import com.douliu.latte.ec.launcher.SignUpDelegate;
import com.mac.latte.core.activities.ProxyActivity;
import com.mac.latte.core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }

}
