package com.mac.latte.core.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by douliu on 2017/9/23.
 */

public class MultiViewHolder extends BaseViewHolder{

    public MultiViewHolder(View view) {
        super(view);
    }

    public static MultiViewHolder create(View view) {
        return new MultiViewHolder(view);
    }

}
