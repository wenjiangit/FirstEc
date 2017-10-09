package com.mac.latte.core.net.interceptors;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mac.latte.core.utils.LattePreferences;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:添加cookie的拦截器
 * Author:douliu
 * Date:Created on 2017/9/28.
 */

public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(LattePreferences.getCustomProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (!TextUtils.isEmpty(s)) {
                            builder.addHeader("cookie", s);
                        }
                    }
                });
        return chain.proceed(builder.build());
    }

}
