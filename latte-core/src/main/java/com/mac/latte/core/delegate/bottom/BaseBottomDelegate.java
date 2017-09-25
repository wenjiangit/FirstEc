package com.mac.latte.core.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.mac.latte.core.R;
import com.mac.latte.core.R2;
import com.mac.latte.core.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 *
 * Created by douliu on 2017/9/15.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final List<BottomItemBean> mItemBeans = new ArrayList<>();
    private final List<BottomItemDelegate> mItemDelegates = new ArrayList<>();
    private final LinkedHashMap<BottomItemBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentIndex = 0;
    @ColorInt
    private int mClickColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayout mBottomBar;

    @ColorInt
    protected abstract int setClickColor();

    protected abstract int setDelegateIndex();

    protected abstract LinkedHashMap<BottomItemBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    protected Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentIndex = setDelegateIndex();
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }

        ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomItemBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomItemBean, BottomItemDelegate> entry : ITEMS.entrySet()) {
            BottomItemBean key = entry.getKey();
            BottomItemDelegate value = entry.getValue();
            mItemBeans.add(key);
            mItemDelegates.add(value);
        }
    }


    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_icon_text_item, mBottomBar);
            RelativeLayout itemView = (RelativeLayout) mBottomBar.getChildAt(i);
            itemView.setTag(i);
            itemView.setOnClickListener(this);
            IconTextView icon = (IconTextView) itemView.getChildAt(0);
            TextView title = (TextView) itemView.getChildAt(1);
            BottomItemBean itemBean = mItemBeans.get(i);
            icon.setText(itemBean.getIcon());
            title.setText(itemBean.getTitle());
            if (mCurrentIndex == i) {
                icon.setTextColor(mClickColor);
                title.setTextColor(mClickColor);
            }
        }

        SupportFragment[] fragments = mItemDelegates.toArray(new SupportFragment[0]);
        loadMultipleRootFragment(R.id.delegate_container,mCurrentIndex,fragments);

    }


    private void resetColor() {
        for (int i = 0; i < mBottomBar.getChildCount(); i++) {
            RelativeLayout itemView = (RelativeLayout) mBottomBar.getChildAt(i);
            setItemColor(itemView, Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        resetColor();
        int tag = (int) v.getTag();
        RelativeLayout item = (RelativeLayout) v;
        setItemColor(item, mClickColor);
        showHideFragment(mItemDelegates.get(tag), mItemDelegates.get(mCurrentIndex));
        mCurrentIndex = tag;
    }

    private void setItemColor(RelativeLayout item, int clickColor) {
        IconTextView icon = (IconTextView) item.getChildAt(0);
        TextView title = (TextView) item.getChildAt(1);
        icon.setTextColor(clickColor);
        title.setTextColor(clickColor);
    }


}
