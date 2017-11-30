package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.CargoEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class Interactor implements IInteractor {

    @Inject
    IDataProvider dataProvider;

    public Interactor() {
        MApplication.getDaggerComponents().inject(this);
    }

    @Override
    public Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable() {
        return dataProvider.getCurrencyTypesObservable();
    }

    @Override
    public Observable<CargoPageResponse> getCargoPageObservable() {
        return dataProvider.getCargoPageObservable();
    }

    @Override
    public Observable<List<CargoEntity>> getCargosObservable() {
        return dataProvider.getCargoPageObservable().flatMap(new Func1<CargoPageResponse, Observable<List<CargoEntity>>>() {
            @Override
            public Observable<List<CargoEntity>> call(CargoPageResponse cargoPageResponse) {
                final List<CargoEntity> result = new ArrayList<>();
                for (final CargoPageResponse.Load item : cargoPageResponse.getLoads()) {
                    result.add(CargoEntity.fromResponseItem(item));
                }
                return Observable.from(result).toList();
            }
        });
    }

    @Override
    public void writeCurrencyTypesToDb(List<CurrencyTypeEntity> currencyTypeEntities) {

    }

    @Override
    public Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservableFromDb() {
        return null;
    }
}
