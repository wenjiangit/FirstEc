package com.mac.latte.core.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 *
 * Created by mac on 2017/8/6.
 */

public abstract class BaseInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        HttpUrl url = chain.request().url();
        final int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameter(Chain chain, String key) {
        HttpUrl url = chain.request().url();
        return url.queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        FormBody body = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        final int size = body.size();
        for (int i = 0; i < size; i++) {
            params.put(body.name(i), body.value(i));
        }
        return params;
    }

    protected String getBodyParameter(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }




}
