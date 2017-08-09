package com.mac.latte.core.net.interceptors;

import android.support.annotation.RawRes;

import com.mac.latte.core.utils.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * Created by mac on 2017/8/6.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String debugUrl;
    private final int rawId;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.debugUrl = debugUrl;
        this.rawId = rawId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        if (url.contains(debugUrl)) {
            return getDebugResponse(chain, rawId);
        }
        return chain.proceed(chain.request());
    }

    private Response getDebugResponse(Chain chain,String json) {
        return new Response.Builder()
                .addHeader("Content-Type", "application/json")
                .body(createResponseBody(json))
                .request(chain.request())
                .code(200)
                .message("ok")
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response getDebugResponse(Chain chain, @RawRes int rawId) {
        String json = FileUtil.getRawFile(rawId);
        return getDebugResponse(chain, json);
    }

    private ResponseBody createResponseBody(String json) {
        return ResponseBody.create(MediaType.parse("application/json"), json);
    }

}
