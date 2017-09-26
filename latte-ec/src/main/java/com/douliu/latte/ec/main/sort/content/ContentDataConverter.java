package com.douliu.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mac.latte.core.utils.Loger;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/26.
 */

class ContentDataConverter {

    private final String jsonData;

    ContentDataConverter(String jsonData) {
        this.jsonData = jsonData;
    }

    List<SectionBean> convert() {
        List<SectionBean> dataList = new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(jsonData).getJSONArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject dataObject = jsonArray.getJSONObject(i);
            Integer id = dataObject.getInteger("id");
            String section = dataObject.getString("section");
            SectionBean sectionBean = new SectionBean(true, section);
            sectionBean.setId(id);
            dataList.add(sectionBean);

            JSONArray goods = dataObject.getJSONArray("goods");
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                JSONObject goodsJSONObject = goods.getJSONObject(j);
                SectionBean.GoodsBean goodsBean = JSON.parseObject(goodsJSONObject.toJSONString(),
                        SectionBean.GoodsBean.class);
                SectionBean bean = new SectionBean(goodsBean);
                dataList.add(bean);
            }
        }
        Loger.d("dataList: ", dataList);
        return dataList;
    }
}
