package com.mac.firstec;

import android.app.Application;

import com.douliu.latte.ec.icon.FontAliModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mac.latte.core.app.Latte;
import com.mac.latte.core.net.interceptors.AddCookieInterceptor;
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
                .withInterceptor(new AddCookieInterceptor())
                .withApiHost("http://116.196.74.143")
                .withWxAppId("wxf0ebff3bd2eedd12")
                .withWxAppSecret("d4624c36b6795d1d99dcf0547af5443d")
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
