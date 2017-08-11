package com.douliu.latte.ec.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.douliu.latte.ec.R;
import com.mac.latte.core.constans.LattePrefKey;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.utils.LattePreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douliu on 2017/8/9.
 *
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner;

    private static final List<Integer> PAGES ;

    static {
        PAGES = new ArrayList<>();
        PAGES.add(R.mipmap.launcher_01);
        PAGES.add(R.mipmap.launcher_02);
        PAGES.add(R.mipmap.launcher_03);
        PAGES.add(R.mipmap.launcher_04);
        PAGES.add(R.mipmap.launcher_05);
    }

    public static LauncherScrollDelegate newInstance() {
        Bundle args = new Bundle();
        LauncherScrollDelegate fragment = new LauncherScrollDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mConvenientBanner.setPages(new HolderCreator(), PAGES)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int i) {
        if (i == PAGES.size() - 1) {
            // TODO: 2017/8/9 跳转到主界面
            LattePreferences.setAppFlag(LattePrefKey.IS_FIRST_ENTER_APP, true);
            Toast.makeText(getContext(), "跳转到主界面", Toast.LENGTH_SHORT).show();
        }

    }


    private static class HolderCreator implements CBViewHolderCreator<LauncherHolder> {
        @Override
        public LauncherHolder createHolder() {
            return new LauncherHolder();
        }
    }


    private static class LauncherHolder implements Holder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            mImageView = new ImageView(context);
            return mImageView;
        }

        @Override
        public void UpdateUI(Context context, int i, Integer integer) {
            mImageView.setImageResource(integer);
        }
    }


}

