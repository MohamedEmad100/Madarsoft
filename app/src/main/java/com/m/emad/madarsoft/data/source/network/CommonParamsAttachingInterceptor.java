package com.m.emad.madarsoft.data.source.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CommonParamsAttachingInterceptor implements Interceptor {

    private final Map<String, String> params;

    public CommonParamsAttachingInterceptor(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl.Builder newUrlBuilder = original.url().newBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            newUrlBuilder.addQueryParameter(param.getKey(), param.getValue());
        }

        return chain.proceed(original.newBuilder().url(newUrlBuilder.build()).build());
    }
}
