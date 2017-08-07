package com.mac.latte.core.net.download;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.mac.latte.core.net.RestCreator;
import com.mac.latte.core.net.RestService;
import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by mac on 2017/8/6.
 */

public class DownloadHandler {

    private final String url;
    private final Map<String, Object> params;
    private final ISuccess success;
    private final IError error;
    private final IFailure failure;
    private final IRequest request;
    private final String downloadDir;
    private final String extension;
    private final String name;

    public DownloadHandler(String url, Map<String, Object> params,
                           ISuccess success, IError error,
                           IFailure failure, IRequest request,
                           String downloadDir, String extension, String name) {
        this.url = url;
        this.params = params;
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
        this.downloadDir = downloadDir;
        this.extension = extension;
        this.name = name;
    }

    public void handleDownload() {
        if (request != null) {
            request.onRequestStart();
        }
        RestService service = RestCreator.getService();
        Call<ResponseBody> call = service.download(url, params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    SaveFileTask task = new SaveFileTask(success, request);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            downloadDir, name, body, extension);
                } else {
                    if (error != null) {
                        error.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                if (failure != null) {
                    failure.onFailure();
                }
            }
        });
    }
}
