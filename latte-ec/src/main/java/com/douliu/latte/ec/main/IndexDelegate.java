package com.douliu.latte.ec.main;

import android.os.Bundle;
import android.view.View;

import com.douliu.latte.ec.R;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;

/**
 *
 * Created by douliu on 2017/9/21.
 */

public class IndexDelegate extends BottomItemDelegate{
    @Override
    protected Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
