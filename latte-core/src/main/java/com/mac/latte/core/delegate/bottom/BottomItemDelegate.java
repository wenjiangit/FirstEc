package com.mac.latte.core.delegate.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.mac.latte.core.R;
import com.mac.latte.core.delegate.LatteDelegate;

/**
 * 主页面几个fragment的基类
 * Created by douliu on 2017/9/15.
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private static final int EXIT_TIME = 2000;
    private long mLastClickTime = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mLastClickTime > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mLastClickTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
            }
            return true;
        }
        return false;
    }

}
