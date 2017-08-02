package com.mac.latte.core.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.mac.latte.core.R;
import com.mac.latte.core.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 *
 * Created by mac on 2017/8/2.
 */

public abstract class ProxyActivity extends SupportActivity{

    protected abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout content = new ContentFrameLayout(this);
        content.setId(R.id.delegate_container);
        setContentView(content);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }
}
