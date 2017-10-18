package com.mac.latte.core.delegate;

/**
 * 基类
 * Created by mac on 2017/8/2.
 */

public abstract class LatteDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
