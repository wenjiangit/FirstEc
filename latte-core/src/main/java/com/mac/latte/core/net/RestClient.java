package com.mac.latte.core.net;

import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class RestClient {

    private final String url;
    private final Map<String,Object> params;
    private final ISuccess success;
    private final IError error;
    private final IFailure failure;
    private final IRequest request;
    private final RequestBody body;


    public RestClient(String url, Map<String, Object> params,
                      ISuccess success, IError error,
                      IFailure failure, IRequest request,
                      RequestBody body) {
        this.url = url;
        this.params = params;
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
        this.body = body;
    }

    public static RestClientBuilder buider() {
        return new RestClientBuilder();
    }
}
