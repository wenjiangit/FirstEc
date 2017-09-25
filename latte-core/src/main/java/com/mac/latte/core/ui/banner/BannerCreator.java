package com.mac.latte.core.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mac.latte.core.R;

import java.util.List;

/**
 * BannerCreator
 * Created by douliu on 2017/9/23.
 */

public class BannerCreator {

    public static void setDefault(List<String> imageUrls, ConvenientBanner<String> banner,
                                  OnItemClickListener listener) {
        banner.setPages(new HolderCreator(), imageUrls)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(listener)
                .setCanLoop(true);
    }
}
