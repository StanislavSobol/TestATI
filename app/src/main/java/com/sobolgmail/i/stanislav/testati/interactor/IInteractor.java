package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.entity.CargoEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IInteractor {

    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();

    Observable<CargoPageResponse> getCargoPageObservable();

    Observable<List<CargoEntity>> getCargosObservable();

    void writeCurrencyTypesToDb(List<CurrencyTypeEntity> currencyTypeEntities);

    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservableFromDb();
}
