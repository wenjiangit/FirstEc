package com.douliu.latte.ec.main.sort.content;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.douliu.latte.ec.R;
import com.mac.latte.core.glide.GlideApp;

import java.util.List;

/**
 * Description:SectionAdapter
 * Author:douliu
 * Date:Created on 2017/9/26.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {

    SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, SectionBean sectionBean) {
        holder.setText(R.id.txt_section, sectionBean.header);
        holder.addOnClickListener(R.id.txt_more);
    }

    @Override
    protected void convert(BaseViewHolder holder, SectionBean sectionBean) {
        holder.setText(R.id.txt_name, sectionBean.t.getGoodsName());
        ImageView view = holder.getView(R.id.img_thumb);
        GlideApp.with(mContext)
                .load(sectionBean.t.getGoodsThumb())
                .centerInside()
                .into(view);
    }
}
