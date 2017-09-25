package com.mac.latte.core.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mac.latte.core.R;
import com.mac.latte.core.glide.GlideApp;
import com.mac.latte.core.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * 多布局的RecyclerAdapter
 * Created by douliu on 2017/9/23.
 */

public class MultiRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultiViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    private boolean isBannerInit = false;

    private static final RequestOptions sDefaultRequestOptions = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    private MultiRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultiRecyclerAdapter create() {
        return new MultiRecyclerAdapter(new ArrayList<MultipleItemEntity>());
    }


    public static MultiRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultiRecyclerAdapter(data);
    }

    public static MultiRecyclerAdapter create(DataConverter converter) {
        return new MultiRecyclerAdapter(converter.convert());
    }

    @Override
    protected MultiViewHolder createBaseViewHolder(View view) {
        return MultiViewHolder.create(view);
    }

    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multi_text);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multi_text_image);
        addItemType(ItemType.IMAGE, R.layout.item_multi_image);
        addItemType(ItemType.BANNER, R.layout.item_multi_banner);

        //开启动画
        openLoadAnimation();
        //设置每一个item所占位置大小
        setSpanSizeLookup(this);
    }


    @Override
    protected void convert(MultiViewHolder holder, MultipleItemEntity entity) {
        String imageUrl;
        String text;
        switch (holder.getItemViewType()) {
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                GlideApp.with(mContext)
                        .load(imageUrl)
                        .apply(sDefaultRequestOptions)
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.txt_single, text);
                break;
            case ItemType.TEXT_IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                GlideApp.with(mContext)
                        .load(imageUrl)
                        .apply(sDefaultRequestOptions)
                        .into((ImageView) holder.getView(R.id.img_show));
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.txt_desc, text);
                break;
            case ItemType.BANNER:
                if (!isBannerInit) {
                    List<String> imageUrls = entity.getField(MultipleFields.BANNERS);
                    ConvenientBanner<String> banner = holder.getView(R.id.banner);
                    BannerCreator.setDefault(imageUrls, banner, this);
                    isBannerInit = true;
                }
                break;
            default:
                break;

        }

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
        return getData().get(i).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int i) {

    }
}
