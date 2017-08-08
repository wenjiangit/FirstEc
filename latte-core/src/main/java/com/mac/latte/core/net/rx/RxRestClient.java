package com.mac.latte.core.net.rx;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mac.latte.core.net.RestCreator;
import com.mac.latte.core.net.callback.HttpMethod;
import com.mac.latte.core.ui.LatteLoader;
import com.mac.latte.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 支持Rxjava的Client
 * Created by mac on 2017/8/2.
 */

public class RxRestClient {

    private final String url;
    private final Map<String, Object> params;
    private final RequestBody body;
    private final Context context;
    private final LoaderStyle style;
    private final File file;


    public RxRestClient(String url, Map<String, Object> params,
                 RequestBody body, Context context,
                 LoaderStyle style, File file) {
        this.url = url;
        this.params = params;
        this.body = body;
        this.context = context;
        this.style = style;
        this.file = file;
    }

    @NonNull
    public static RxRestClientBuilder buider() {
        return new RxRestClientBuilder();
    }


    private Observable<String> request(HttpMethod method) {
        RxRestService service = RestCreator.getRxService();
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(url, params);
                break;
            case POST:
                observable = service.post(url, params);
                break;
            case DELETE:
                observable = service.delete(url, params);
                break;
            case PUT:
                observable = service.put(url, params);
                break;
            case PUT_RAW:
                observable = service.putRaw(url, body);
                break;
            case POST_RAW:
                observable = service.postRaw(url, body);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
                MultipartBody.Part part = MultipartBody.Part.create(requestBody);
                observable = service.upload(url, part);
                break;
            default:
                break;
        }
        if (style != null) {
            LatteLoader.showLoading(context, style);
        }
        return observable;
    }

    public Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public Observable<String> post() {
        if (body == null) {
            return request(HttpMethod.POST);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public Observable<String> put() {
        if (body == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public Observable<ResponseBody> download() {
        RxRestService rxService = RestCreator.getRxService();
        return rxService.download(url, params);
    }

}
