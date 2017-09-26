package com.mac.latte.core.delegate;

/**
 *
 * Created by mac on 2017/8/2.
 */

public abstract class LatteDelegate extends BaseDelegate{

    @SuppressWarnings("unchecked")
    protected  <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
