package com.mac.latte.core.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Created by douliu on 2017/9/22.
 */

public class MultipleItemEntity implements MultiItemEntity{

    private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        this.FIELDS.putAll(fields);
    }

    public static MultiEntityBuilder builder() {
        return new MultiEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS.get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(Object key) {
        return (T) FIELDS.get(key);
    }

    public void setField(Object key, Object value) {
        FIELDS.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> entry : FIELDS.entrySet()) {
            sb.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }

}
