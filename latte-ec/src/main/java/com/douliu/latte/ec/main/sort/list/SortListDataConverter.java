package com.douliu.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mac.latte.core.recycler.DataConverter;
import com.mac.latte.core.recycler.ItemType;
import com.mac.latte.core.recycler.MultipleFields;
import com.mac.latte.core.recycler.MultipleItemEntity;

import java.util.List;

/**
 * Description:分类列表数据转换
 * Author:douliu
 * Date:Created on 2017/9/26.
 */

public class SortListDataConverter extends DataConverter {
    @Override
    protected List<MultipleItemEntity> convert() {
        JSONArray jsonArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString(MultipleFields.NAME);
            Integer id = jsonObject.getInteger(MultipleFields.ID);

            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .addField(MultipleFields.ITEM_TYPE, ItemType.MENU_LIST)
                    .addField(MultipleFields.NAME, name)
                    .addField(MultipleFields.ID, id)
                    .addField(MultipleFields.TAG, false)
                    .build();

            mEntities.add(itemEntity);
        }
        mEntities.get(0).setField(MultipleFields.TAG, true);
        return mEntities;
    }
}
