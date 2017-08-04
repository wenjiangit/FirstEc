package com.mac.firstec;

import com.mac.latte.core.activities.ProxyActivity;
import com.mac.latte.core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
