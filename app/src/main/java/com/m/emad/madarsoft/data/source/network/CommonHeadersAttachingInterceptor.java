package com.m.emad.madarsoft.data.source.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CommonHeadersAttachingInterceptor implements Interceptor {
    private final Map<String, String> headers;

    public CommonHeadersAttachingInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();

        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestBuilder.addHeader(header.getKey(), header.getValue());
            }
        }

        return chain.proceed(requestBuilder.build());
    }
}
