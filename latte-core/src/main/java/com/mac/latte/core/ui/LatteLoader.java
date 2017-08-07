package com.mac.latte.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mac.latte.core.R;
import com.mac.latte.core.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by mac on 2017/8/5.
 */

public class LatteLoader {

    private static final int SCREEN_SCALE = 8;
    private static final int HEIGHT_OFFSET_SCALE = 10;

    private static final List<Dialog> DIALOGS = new ArrayList<>();

    private static final LoaderStyle DEFAULT_STYLE = LoaderStyle.BallClipRotatePulseIndicator;


    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context,R.style.dialog);

        AVLoadingIndicatorView loadingIndicatorView = LoaderCreator.create(context, type);
        dialog.setContentView(loadingIndicatorView);

        Window window = dialog.getWindow();
        if (window != null) {
            int screenHeight = DimenUtil.getScreenHeight();
            int screenWidth = DimenUtil.getScreenWidth();

            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = screenWidth / SCREEN_SCALE;
            attributes.height = screenHeight / SCREEN_SCALE;

            attributes.gravity = Gravity.CENTER;
            attributes.height = attributes.height + screenHeight / HEIGHT_OFFSET_SCALE;
        }

        DIALOGS.add(dialog);
        dialog.show();

    }

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());

    }


    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_STYLE.name());
    }


    public static void stopLoading() {
        for (Dialog dialog : DIALOGS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        }
    }


}
