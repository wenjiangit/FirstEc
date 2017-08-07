package com.mac.latte.core.net;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mac.latte.core.net.callback.HttpMethod;
import com.mac.latte.core.net.callback.IError;
import com.mac.latte.core.net.callback.IFailure;
import com.mac.latte.core.net.callback.IRequest;
import com.mac.latte.core.net.callback.ISuccess;
import com.mac.latte.core.net.callback.RequestCallback;
import com.mac.latte.core.net.download.DownloadHandler;
import com.mac.latte.core.ui.LatteLoader;
import com.mac.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
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
    private final Context context;
    private final LoaderStyle style;
    private final File file;
    private final String downloadDir;
    private final String extension;
    private final String name;



    RestClient(String url, Map<String, Object> params,
               ISuccess success, IError error,
               IFailure failure, IRequest request,
               RequestBody body, Context context,
               LoaderStyle style, File file,
               String downloadDir, String extension,
               String name) {
        this.url = url;
        this.params = params;
        this.success = success;
        this.error = error;
        this.failure = failure;
        this.request = request;
        this.body = body;
        this.context = context;
        this.style = style;
        this.file = file;
        this.downloadDir = downloadDir;
        this.extension = extension;
        this.name = name;
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
            case PUT_RAW:
                call = service.putRaw(url, body);
                break;
            case POST_RAW:
                call = service.postRaw(url, body);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
                MultipartBody.Part part = MultipartBody.Part.create(requestBody);
                call = service.upload(url, part);
                break;
            default:
                break;
        }

        if (call != null) {
            if (request != null) {
                request.onRequestStart();
            }

            if (style != null) {
                LatteLoader.showLoading(context, style);
            }

            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(success, error, failure, request, style);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (body == null) {
            request(HttpMethod.POST);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public void put() {
        if (body == null) {
            request(HttpMethod.PUT);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }

    public void upload() {
        request(HttpMethod.UPLOAD);
    }

    public void download() {
        new DownloadHandler(url, params, success, error, failure,
                request,downloadDir,extension,name)
                .handleDownload();
    }


}
