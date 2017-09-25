package com.mac.latte.core.recycler;


import java.util.LinkedHashMap;

/**
 *
 * Created by douliu on 2017/9/22.
 */

public class MultiEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    MultiEntityBuilder() {
        FIELDS.clear();
    }

    public MultiEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public MultiEntityBuilder addField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

     public MultiEntityBuilder addFields(LinkedHashMap<Object, Object> fields) {
        FIELDS.putAll(fields);
        return this;
    }

    public MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }



}
