package com.mac.latte.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 * Created by mac on 2017/7/31.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    /**
     * 返回应用级的Context
     * @return ApplicationContext
     */
    public static Context getApplication() {
        return Configurator.getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }



}
