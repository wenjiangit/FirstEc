package com.mac.firstec;

import android.app.Application;

import com.joanzapata.android.iconify.fonts.FontAwesomeModule;
import com.mac.latte.core.app.Latte;

/**
 * 自定义Application
 * Created by mac on 2017/8/1.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://localhost:8080")
                .configure();
    }
}
