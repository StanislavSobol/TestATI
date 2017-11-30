package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.CargoPageEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.utils.Logger;
import com.sobolgmail.i.stanislav.testati.utils.StringUtils;

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
    private static final String CURRENCY_TYPES_URL = "http://api.ati.su/v1.0/";
    private static final String CARGOS_URL = "http://loads.ati.su/api/";

    private Retrofit retrofit;
    private RetrofitAPIService currencyTypeService;
    private RetrofitAPIService cargosService;

    @Override
    public Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable() {
        return getCurrencyTypeService().getCurrencyTypesObservable();
    }

    @Override
    public Observable<CargoPageEntity> getCargoPageObservable() {
        return getCargosService().getCargoPageObservable("application/json", new PostBody());
    }

    private RetrofitAPIService getCurrencyTypeService() {
        if (currencyTypeService == null) {
            currencyTypeService = getRetrofit(CURRENCY_TYPES_URL).create(RetrofitAPIService.class);
        }
        return currencyTypeService;
    }

    private RetrofitAPIService getCargosService() {
        if (cargosService == null) {
            cargosService = getRetrofit(CARGOS_URL).create(RetrofitAPIService.class);
        }
        return cargosService;
    }

    private Retrofit getRetrofit(final String baseUrl) {
        if (retrofit == null) {
            retrofit = getNewRetrofit(baseUrl);
        }
        return retrofit;
    }

    private Retrofit getNewRetrofit(final String baseUrl) {
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
                final List<String> list = StringUtils.sliceString(message, 1024);
                for (final String item : list) {
                    Logger.writeHttp(item);
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private Gson getGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(CargoPageEntity.class, new CargoPageEntityDeserializer());
        return gsonBuilder.create();
    }

//    private static class CargoPageEntityDeserializer implements JsonDeserializer<CargoPageEntity> {
//
//        private final Gson gson_ = new GsonBuilder().create();
//
//        @Override
//        public CargoPageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            return null;
//        }
//    }
}
