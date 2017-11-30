package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.sobolgmail.i.stanislav.testati.entity.CargoPageEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface RetrofitAPIService {
    @GET("dictionaries/currencyTypes")
    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();

    @POST("loads")
    Observable<CargoPageEntity> getCargosObservable(@Header("Content-Type") String contentType, @Body PostBody postBody);
}
