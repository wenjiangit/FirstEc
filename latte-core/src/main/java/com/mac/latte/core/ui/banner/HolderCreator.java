package com.mac.latte.core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by douliu on 2017/9/23.
 */

public class HolderCreator implements CBViewHolderCreator<ImageViewHolder> {

    @Override
    public ImageViewHolder createHolder() {
        return new ImageViewHolder();
    }
}
