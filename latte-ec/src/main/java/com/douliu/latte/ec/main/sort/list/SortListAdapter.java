package com.douliu.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.douliu.latte.ec.R;
import com.douliu.latte.ec.main.sort.content.SortContentDelegate;
import com.mac.latte.core.delegate.LatteDelegate;
import com.mac.latte.core.recycler.ItemType;
import com.mac.latte.core.recycler.MultiRecyclerAdapter;
import com.mac.latte.core.recycler.MultiViewHolder;
import com.mac.latte.core.recycler.MultipleFields;
import com.mac.latte.core.recycler.MultipleItemEntity;

import java.util.List;

/**
 * Description:分类列表适配器
 * Author:douliu
 * Date:Created on 2017/9/26.
 */

public class SortListAdapter extends MultiRecyclerAdapter implements View.OnClickListener {

    private LatteDelegate mParentDelegate;

    SortListAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        mParentDelegate = delegate;
        addItemType(ItemType.MENU_LIST, R.layout.item_sort_menu_list);
    }

    //先前点击位置
    private int mPrePosition = 0;


    @Override
    protected void convert(final MultiViewHolder holder, final MultipleItemEntity entity) {
//        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.MENU_LIST:
                final int id = entity.getField(MultipleFields.ID);
                boolean clicked = entity.getField(MultipleFields.TAG);
                String name = entity.getField(MultipleFields.NAME);
                TextView txtName = holder.getView(R.id.txt_name);
                View indicator = holder.getView(R.id.indicator);
                View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        if (position != mPrePosition) {
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(position);
                            mPrePosition = position;
                        }
                        SortContentDelegate delegate = SortContentDelegate.newInstance(id);
                        switchDelegate(delegate);
                    }
                });
                if (clicked) {
                    txtName.setTextColor(ContextCompat.getColor(mContext, R.color.orange_300));
                    indicator.setBackgroundColor(ContextCompat.getColor(mContext, R.color.orange_300));
                    itemView.setBackgroundColor(Color.WHITE);
                    indicator.setVisibility(View.VISIBLE);
                } else {
                    txtName.setTextColor(ContextCompat.getColor(mContext, R.color.grey_800));
                    indicator.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_500));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_200));
                    indicator.setVisibility(View.INVISIBLE);
                }
                txtName.setText(name);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    private void switchDelegate(LatteDelegate delegate) {
        SortContentDelegate contentDelegate = mParentDelegate.findChildFragment(SortContentDelegate.class);
        if (delegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
