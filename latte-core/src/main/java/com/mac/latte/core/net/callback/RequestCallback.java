package com.mac.latte.core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mac on 2017/8/3.
 */

public class RequestCallback implements Callback<String>{

    private final ISuccess success;
    private final IError error;
    private final IFailure failure;
    private final IRequest request;

    public RequestCallback(ISuccess success, IError error, IFailure failure, IRequest request) {
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (success != null) {
                    success.onSuccess(response.body());
                }
            }
        } else {
            if (error != null) {
                error.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        throwable.printStackTrace();
        if (failure != null) {
            failure.onFailure();
        }
        if (request != null) {
            request.onRequestEnd();
        }

    }
}
