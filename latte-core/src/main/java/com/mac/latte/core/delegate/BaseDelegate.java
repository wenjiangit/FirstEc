package com.mac.latte.core.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mac.latte.core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @desc 基类fragment
 * @author wenjian
 * @date on 2017/8/2.
 */

public abstract class BaseDelegate extends SwipeBackFragment{

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder mUnbinder;

    /**
     * 设置布局
     * @return 布局id或view
     */
    protected abstract Object setLayout();

    /**
     * 绑定控件
     * @param savedInstanceState Bundle
     * @param rootView 根布局
     */
    protected abstract void onBindView(Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() should be View or layoutId");
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    protected ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
