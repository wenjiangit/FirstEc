package com.mac.latte.core.net;

import android.support.annotation.NonNull;

import com.mac.latte.core.net.callback.HttpMethod;
import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.net.callback.RequestCallback;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 *
 * Created by mac on 2017/8/2.
 */

public class RestClient {

    private final String url;
    private final Map<String, Object> params;
    private final ISuccess success;
    private final IError error;
    private final IFailure failure;
    private final IRequest request;
    private final RequestBody body;


    RestClient(String url, Map<String, Object> params,
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

    @NonNull
    public static RestClientBuilder buider() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {

        RestService service = RestCreator.getService();
        Call<String> call = null;
        switch (method) {
            case GET:
                call = service.get(url, params);
                break;
            case POST:
                call = service.post(url, params);
                break;
            case DELETE:
                call = service.delete(url, params);
                break;
            case PUT:
                call = service.put(url, params);
                break;
            default:
                break;
        }

        if (call != null) {
            if (request != null) {
                request.onRequestStart();
            }
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(success, error, failure, request);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        request(HttpMethod.POST);
    }

    public void put() {
        request(HttpMethod.PUT);
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }


}
