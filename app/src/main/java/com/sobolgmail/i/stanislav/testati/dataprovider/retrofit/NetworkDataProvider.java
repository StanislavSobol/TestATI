package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class NetworkDataProvider implements IDataProvider {

    private static final long NETWORK_TIMEOUT_MS = 60 * 1000;
    private static final String BASE_URL = "http://api.ati.su/v1.0/";

    private Retrofit retrofit;
    private RetrofitAPIService service;

    @Override
    public Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable() {
        return getService().getCurrencyTypesObservable();
    }

    private RetrofitAPIService getService() {
        if (service == null) {
            service = getRetrofit().create(RetrofitAPIService.class);
        }
        return service;
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = getNewRetrofit(BASE_URL);
        }
        return retrofit;
    }

    private Retrofit getNewRetrofit(String baseUrl) {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(rxAdapter)
                .client(getOkHttpClient())
                .build();
    }


    private OkHttpClient getOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.writeHttp(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
                .build();
    }

    private Gson getGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
