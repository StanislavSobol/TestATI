package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IInteractor {

    Observable<List<CurrencyTypeModel>> getCurrencyTypesObservable();

    Observable<List<CargoModel>> getCargosObservable();

    Observable<Void> writeCurrencyTypesToDb(List<CurrencyTypeModel> models);
}
