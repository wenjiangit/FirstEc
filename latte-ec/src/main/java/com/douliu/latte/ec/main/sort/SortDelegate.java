package com.douliu.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.main.sort.content.SortContentDelegate;
import com.douliu.latte.ec.main.sort.list.SortListDelegate;
import com.mac.latte.core.delegate.bottom.BottomItemDelegate;

/**
 * Description:分类界面
 * Author:douliu
 * Date:Created on 2017/9/25.
 */

public class SortDelegate extends BottomItemDelegate{

    public static SortDelegate create() {
        return new SortDelegate();
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        loadRootFragment(R.id.lay_list_container, SortListDelegate.newInstance());
        loadRootFragment(R.id.lay_content_container, SortContentDelegate.newInstance(1));
    }
}
