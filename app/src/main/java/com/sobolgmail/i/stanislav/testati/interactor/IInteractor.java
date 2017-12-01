package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IInteractor {

    Observable<List<CurrencyTypeModel>> getCurrencyTypesObservable();

    Observable<List<CargoModel>> getCargosObservable();

    Observable<Void> writeCurrencyTypesToDb(List<CurrencyTypeModel> models);

    Observable<Void> writeCargosToDb(List<CargoModel> models);

    Observable<List<CargoModel>> loadCargosFromDb();

    Observable<CargoModel> getCargoObservable(String id);
}
