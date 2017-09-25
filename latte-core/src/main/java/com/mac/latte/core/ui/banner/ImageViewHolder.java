package com.mac.latte.core.ui.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by douliu on 2017/9/23.
 */

public class ImageViewHolder implements Holder<String> {

    private ImageView mImageView;

    private RequestOptions mRequestOptions = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int i, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .apply(mRequestOptions)
                .into(mImageView);
    }
}
