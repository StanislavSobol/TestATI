package com.sobolgmail.i.stanislav.testati.retrofit;

import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface BaseAPIService {
    @GET("dictionaries/currencyTypes")
    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();
}
