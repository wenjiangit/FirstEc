package com.mac.latte.core.net;

import android.content.Context;

import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by mac on 2017/8/2.
 */

public class RestClientBuilder {

    private String mUrl;
    private final Map<String, Object> mParams = new WeakHashMap<>();
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private IRequest mIRequest;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;
    private String downloadDir;
    private String extension;
    private String name;


    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder params(String key, String value) {
        this.mParams.put(key, value);
        return this;
    }

    public RestClientBuilder params(Map<String, Object> params) {
        if (params != null) {
            this.mParams.putAll(params);
        }
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public RestClientBuilder downloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
        return this;
    }

    public RestClientBuilder fileName(String name) {
        this.name = name;
        return this;
    }

    public RestClientBuilder extension(String extension) {
        this.extension = extension;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    public RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    public RestClientBuilder raw(String raw) {
        this.mBody = MultipartBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public RestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RestClientBuilder file(String fileName) {
        this.mFile = new File(fileName);
        return this;
    }


    public RestClient build() {
        return new RestClient(mUrl,
                mParams,
                mISuccess,
                mIError,
                mIFailure,
                mIRequest,
                mBody,
                mContext,
                mLoaderStyle,
                mFile,
                downloadDir,
                extension,
                name);
    }


}
