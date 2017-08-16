package com.mac.latte.core.net.callback;

import android.support.annotation.NonNull;

import com.mac.latte.core.ui.LatteLoader;
import com.mac.latte.core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by mac on 2017/8/3.
 */

public class RequestCallback implements Callback<String> {

    private final ISuccess success;
    private final IError error;
    private final IFailure failure;
    private final IRequest request;
    private final LoaderStyle style;

    public RequestCallback(ISuccess success, IError error,
                           IFailure failure, IRequest request,
                           LoaderStyle style) {
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
        this.style = style;
    }


    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        stopLoading();
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (success != null) {
                    success.onSuccess(response.body());
                }
            }
        } else {
            if (error != null) {
                error.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
        stopLoading();
        throwable.printStackTrace();
        if (failure != null) {
            failure.onFailure();
        }
        if (request != null) {
            request.onRequestEnd();
        }

    }

    private void stopLoading() {
        if (style == null) {
            return;
        }
        LatteLoader.stopLoading();
    }
}
