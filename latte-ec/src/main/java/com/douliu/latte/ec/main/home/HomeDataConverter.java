package com.douliu.latte.ec.main.home;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mac.latte.core.recycler.DataConverter;
import com.mac.latte.core.recycler.ItemType;
import com.mac.latte.core.recycler.MultipleFields;
import com.mac.latte.core.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by douliu on 2017/9/22.
 */

public class HomeDataConverter extends DataConverter {

    @Override
    protected List<MultipleItemEntity> convert() {
        String jsonData = getJsonData();
        JSONArray jsonArray = JSON.parseObject(jsonData).getJSONArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            String text = data.getString(MultipleFields.TEXT);
            Integer goodsId = data.getInteger(MultipleFields.ID);
            String imageUrl = data.getString(MultipleFields.IMAGE_URL);
            JSONArray banners = data.getJSONArray(MultipleFields.BANNERS);
            Integer spanSize = data.getInteger(MultipleFields.SPAN_SIZE);

            List<String> bannerUrls = new ArrayList<>();

            int type = 0;
            if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(imageUrl)) {
                type = ItemType.TEXT_IMAGE;
            } else if (!TextUtils.isEmpty(text)) {
                type = ItemType.TEXT;
            } else if (!TextUtils.isEmpty(imageUrl)) {
                type = ItemType.IMAGE;
            } else if (banners != null){
                type = ItemType.BANNER;
                for (int j = 0; j < banners.size(); j++) {
                    String url = banners.getString(i);
                    bannerUrls.add(url);
                }
            }

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .addField(MultipleFields.SPAN_SIZE, spanSize)
                    .addField(MultipleFields.BANNERS, bannerUrls)
                    .addField(MultipleFields.IMAGE_URL, imageUrl)
                    .addField(MultipleFields.ID, goodsId)
                    .addField(MultipleFields.TEXT, text)
                    .addField(MultipleFields.ITEM_TYPE, type)
                    .build();
            mEntities.add(itemEntity);
        }
        return mEntities;
    }
}
