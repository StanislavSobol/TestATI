package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

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
    Observable<List<CurrencyTypeResponse>> getCurrencyTypesObservable();

    @POST("loads")
    Observable<CargoPageResponse> getCargoPageObservable(@Header("Content-Type") String contentType, @Body PostBody postBody);
}
