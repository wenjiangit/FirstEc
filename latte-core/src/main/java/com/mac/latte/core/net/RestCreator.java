package com.mac.latte.core.net;

import com.mac.latte.core.app.Latte;
import com.mac.latte.core.net.rx.RxRestService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mac on 2017/8/2.
 */

public class RestCreator {

    public static RestService getService() {
        return ServiceHolder.REST_SERVICE;
    }

    public static RxRestService getRxService() {
        return ServiceHolder.RX_REST_SERVICE;
    }

    private static class RetrofitHolder {
        private static final String BASE_URL = Latte.getApiHost();
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class OkhttpHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        private static OkHttpClient.Builder addInterceptor() {
            List<Interceptor> interceptors = Latte.getInterceptors();
            if (interceptors != null && !interceptors.isEmpty()) {
                for (Interceptor interceptor : interceptors) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
    }


    private static class ServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(RestService.class);
        private static final RxRestService RX_REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(RxRestService.class);
    }
}
