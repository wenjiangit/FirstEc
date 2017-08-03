package com.mac.firstec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mac.latte.core.activities.ProxyActivity;
import com.mac.latte.core.app.Latte;
import com.mac.latte.core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
