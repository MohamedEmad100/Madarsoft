package com.m.emad.madarsoft.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m.emad.madarsoft.BuildConfig;
import com.m.emad.madarsoft.data.source.network.ApiClient;
import com.m.emad.madarsoft.data.source.network.CommonHeadersAttachingInterceptor;
import com.m.emad.madarsoft.data.source.network.CommonParamsAttachingInterceptor;
import com.m.emad.madarsoft.data.source.network.RequestConfig;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    /* URLs
     * NOTE: Must end with a forward slash '/'
     * */


    @Inject
    public RequestManager() { }


    public RequestConfig generateRequestConfig() {
        RequestConfig commonConfig = new RequestConfig() ;

        // Allowing children classes to update the config object, couple of things are to be noted here
        // 1- This is done before adding the headers interceptor so that it includes all headers (common and child's)
        // 2- Any Converters added by children will be added first and has priority over the default converter
        // 3- Any CallAdapters added by children will be added first and has priority over default call adapter
        commonConfig = updateRequestConfig(commonConfig);

        // Adding Header interceptor to add all headers in the config object to the request
        commonConfig.addInterceptor(new CommonHeadersAttachingInterceptor(commonConfig.getHeaders()));
        //Adding Params interceptor to add all common params
        commonConfig.addInterceptor(new CommonParamsAttachingInterceptor(commonConfig.getParams()));

        // Adding request/response logger in debug builds
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            commonConfig.addInterceptor(loggingInterceptor);
        }

        // Adding default converters and call adapters

        commonConfig.addConverterFactory(GsonConverterFactory.create(getCommonGsonInstance()));

        commonConfig.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        return commonConfig;
    }

    private Gson getCommonGsonInstance() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    /**
     * Helper method for creating a Retrofit service interface implementation
     *
     * @param clazz Retrofit service interface class object
     * @param <T>   Retrofit service interface type
     * @return An implementation of Retrofit service interface
     */

    public <T> T startRequest(Class<T> clazz) {
        return new ApiClient(generateRequestConfig()).createService(clazz);
    }

    public <T> T startRequest(Class<T> clazz, RequestConfig requestConfig) {
        return new ApiClient(requestConfig).createService(clazz);
    }


    public RequestConfig updateRequestConfig(RequestConfig requestConfig) {
        return requestConfig;
    }


}
