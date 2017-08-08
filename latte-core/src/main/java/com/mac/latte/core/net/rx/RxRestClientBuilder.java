package com.mac.latte.core.net.rx;

import android.content.Context;

import com.mac.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class RxRestClientBuilder {

    private String mUrl;
    private final Map<String, Object> mParams = new WeakHashMap<>();
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;

    public RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RxRestClientBuilder params(String key, String value) {
        this.mParams.put(key, value);
        return this;
    }

    public RxRestClientBuilder params(Map<String, Object> params) {
        if (params != null) {
            this.mParams.putAll(params);
        }
        return this;
    }

    public RxRestClientBuilder raw(String raw) {
        this.mBody = MultipartBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public RxRestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RxRestClientBuilder file(String fileName) {
        this.mFile = new File(fileName);
        return this;
    }

    public RxRestClient build() {
        return new RxRestClient(mUrl,
                mParams,
                mBody,
                mContext,
                mLoaderStyle,
                mFile);
    }


}
