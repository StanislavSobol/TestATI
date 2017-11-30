package com.sobolgmail.i.stanislav.testati.dataprovider;

import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IDataProvider {
    long NETWORK_TIMEOUT_MS = 60 * 1000;
    String CURRENCY_TYPES_URL = "http://api.ati.su/v1.0/";
    String CARGOS_URL = "http://loads.ati.su/api/";

    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();

    Observable<CargoPageResponse> getCargoPageObservable();
}
