package com.mac.latte.core.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Description:RecyclerView分隔线
 * Author:douliu
 * Created on 2017/9/25.
 */

public class LatteItemDecoration extends DividerItemDecoration {

    private LatteItemDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookup(color, size));
    }

    public static LatteItemDecoration create(@ColorInt int color, int size) {
        return new LatteItemDecoration(color, size);
    }

    private static class DividerLookup implements DividerItemDecoration.DividerLookup {

        private final int color;

        private final int size;

        DividerLookup(@ColorInt int color, int size) {
            this.color = color;
            this.size = size;
        }

        @Override
        public Divider getVerticalDivider(int i) {
            return new Divider.Builder()
                    .color(color)
                    .size(size)
                    .build();
        }

        @Override
        public Divider getHorizontalDivider(int i) {
            return new Divider.Builder()
                    .color(color)
                    .size(size)
                    .build();
        }
    }

}
