package com.mac.latte.core.recycler;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by douliu on 2017/9/22.
 */

public abstract class DataConverter {

    protected final List<MultipleItemEntity> mEntities = new ArrayList<>();

    private String mJsonData;

    public DataConverter setJsonData(String jsonData) {
        mJsonData = jsonData;
        return this;
    }

    protected abstract List<MultipleItemEntity> convert();

    protected String getJsonData() {
        if (TextUtils.isEmpty(mJsonData)) {
            throw new NullPointerException("json data is null");
        }
        return mJsonData;
    }

    public List<MultipleItemEntity> getEntities() {
        return convert();
    }

    public void clearData() {
        mEntities.clear();
    }
}
