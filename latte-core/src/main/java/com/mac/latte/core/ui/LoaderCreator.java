package com.mac.latte.core.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 *
 * Created by mac on 2017/8/5.
 */

public class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADERS = new WeakHashMap<>();

    public static AVLoadingIndicatorView create(Context context, String type) {
        final AVLoadingIndicatorView loadingIndicatorView = new AVLoadingIndicatorView(context);
        Indicator indicator = LOADERS.get(type);
        if (indicator == null) {
            indicator = getIndicator(type);
            LOADERS.put(type, indicator);
        }
        loadingIndicatorView.setIndicator(indicator);
        return loadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            String pkgName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(pkgName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            Class<?> clazz = Class.forName(drawableClassName.toString());
            return (Indicator) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
