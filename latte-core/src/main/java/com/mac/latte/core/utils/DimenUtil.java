package com.mac.latte.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mac.latte.core.app.Latte;

/**
 *
 * Created by mac on 2017/8/5.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        Resources resources = Latte.getApplication().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        Resources resources = Latte.getApplication().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }

}
