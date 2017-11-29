package com.sobolgmail.i.stanislav.testati.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class NetworkManager {

    private static final long NETWORK_TIMEOUT_MS = 60 * 1000;
    private static final String BASE_URL = "http://api.ati.su/v1.0/";

    private static Retrofit retrofit;
    private static BaseAPIService service;

    public static BaseAPIService getService() {
        if (service == null) {
            service = getRetrofit().create(BaseAPIService.class);
        }
        return service;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = getNewRetrofit(BASE_URL);
        }
        return retrofit;
    }

    private static Retrofit getNewRetrofit(String baseUrl) {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(rxAdapter)
                .client(getOkHttpClient())
                .build();
    }


    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    private static Gson getGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

}
