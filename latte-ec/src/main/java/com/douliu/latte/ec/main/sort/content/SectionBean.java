package com.douliu.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Description:SectionBean
 * Author:douliu
 * Date:Created on 2017/9/26.
 */

public class SectionBean extends SectionEntity<SectionBean.GoodsBean>{

    private int id;
    private String section;
    private GoodsBean goods;

    public SectionBean(GoodsBean goodsBean) {
        super(goodsBean);
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }


    public static class GoodsBean {

        private int goods_id;
        private String goods_thumb;
        private String goods_name;

        public int getGoodsId() {
            return goods_id;
        }

        public void setGoodsId(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoodsThumb() {
            return goods_thumb;
        }

        public void setGoodsThumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoodsName() {
            return goods_name;
        }

        public void setGoodsName(String goods_name) {
            this.goods_name = goods_name;
        }

        @Override
        public String toString() {
            return "GoodsBean{" +
                    "goods_id=" + goods_id +
                    ", goods_thumb='" + goods_thumb + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "SectionBean{" +
                "id=" + id +
                ", section='" + section + '\'' +
                ", goods=" + goods +
                '}';
    }
}
