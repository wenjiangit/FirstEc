package com.mac.firstec;

import android.app.Application;

import com.douliu.latte.ec.icon.FontAliModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mac.latte.core.app.Latte;
import com.mac.latte.core.net.interceptors.DebugInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.raizlabs.android.dbflow.config.FlowManager;

import me.yokeyword.fragmentation.Fragmentation;

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
                .withIcon(new FontAliModule())
                .withInterceptor(new DebugInterceptor("test",R.raw.test))
                .withApiHost("http://localhost:8080")
                .configure();

        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();

        FlowManager.init(this);

        //日志的初始化
        Logger.addLogAdapter(new AndroidLogAdapter());

    }
}
