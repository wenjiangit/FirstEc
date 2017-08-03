package com.mac.latte.core.net;

import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class RestClientBuilder {

    private String url;
    private Map<String, Object> params = new WeakHashMap<>();
    private ISuccess success;
    private IError error;
    private IFailure failure;
    private IRequest request;
    private RequestBody body;

    public RestClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RestClientBuilder params(String key, String value) {
        checkParams();
        this.params.put(key, value);
        return this;
    }

    public RestClientBuilder params(Map<String, Object> params) {
        checkParams();
        this.params.putAll(params);
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.success = success;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.error = error;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.failure = failure;
        return this;
    }

    public RestClientBuilder request(IRequest request) {
        this.request = request;
        return this;
    }

    public RestClientBuilder raw(String raw) {
        this.body = MultipartBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public RestClientBuilder body(RequestBody body) {
        this.body = body;
        return this;
    }

    public RestClient build() {
        return new RestClient(url, params, success, error, failure, request, body);
    }

    private void checkParams() {
        if (params == null) {
            params = new WeakHashMap<>();
        }
    }


}
