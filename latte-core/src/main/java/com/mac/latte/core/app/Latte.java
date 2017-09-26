package com.mac.latte.core.app;

import android.content.Context;

import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 *
 * Created by mac on 2017/7/31.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKey.APPLICATION_CONTEXT,
                context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static Map<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }


    @SuppressWarnings("unchecked")
    public static <T> T getConfiguration(ConfigKey configKey){
        return (T) getConfigurations().get(configKey);
    }

    /**
     * 获取应用级的Context
     * @return ApplicationContext
     */
    public static Context getApplication() {
        return Configurator.getConfiguration(ConfigKey.APPLICATION_CONTEXT);
    }

    public static List<Interceptor> getInterceptors() {
        return Configurator.getConfiguration(ConfigKey.INTERCEPTOR);
    }

    /**
     * 获取主机域名
     * @return 主机域名
     */
    public static String getApiHost() {
        return Configurator.getConfiguration(ConfigKey.API_HOST);
    }



}
