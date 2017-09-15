package com.mac.latte.core.app;


import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * 配置器，配置一些全局通用的东西
 * Created by mac on 2017/7/31.
 */

public class Configurator {

    private static final Map<Object, Object> LATTE_CONFIGS = new HashMap<>();

    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final List<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKey.CONFIG_READY, false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKey.CONFIG_READY, true);
    }

    Map<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    @SuppressWarnings("SameParameterValue")
    public Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKey.API_HOST, host);
        return this;
    }

    /**
     * 初始化矢量图标
     */
    private static void initIcons() {
        if (ICONS.size() > 0) {
            for (IconFontDescriptor descriptor : ICONS) {
                Iconify.with(descriptor);
            }
        }
    }


    public Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withInterceptor(List<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withWxAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKey.WX_APP_ID, appId);
        return this;
    }

    public Configurator withWxAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKey.WX_APP_SECRET, appSecret);
        return this;
    }

    public Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKey.ACTIVITY, activity);
        return this;
    }


    public Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    @SuppressWarnings("unchecked")
    static <T> T getConfiguration(ConfigKey type) {
        checkConfigReady();
        return (T) LATTE_CONFIGS.get(type);
    }

    private static void checkConfigReady() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKey.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,please call configure");
        }
    }
}
