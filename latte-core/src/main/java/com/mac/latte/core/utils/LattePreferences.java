package com.mac.latte.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mac.latte.core.app.Latte;

/**
 *
 * 缓存
 * Created by douliu on 2017/8/9.
 */

public class LattePreferences {

    private static final String PREFERENCE_NAME = "latte_pref";

    private static SharedPreferences getPreferences() {
        return Latte.getApplication().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    public static void setAppFlag(String key, boolean flag) {
        getPreferences().edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static void setCustomProfile(String key, String value) {
        getPreferences().edit()
                .putString(key, value)
                .apply();
    }

    public static String getCustomProfile(String key) {
        return getPreferences().getString(key, "");
    }


}
