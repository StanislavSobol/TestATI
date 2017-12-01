package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.dataprovider.db.ISqlOrmManager;
import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;

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

    @Inject
    ISqlOrmManager sqlOrmManager;

    public Interactor() {
        MApplication.getDaggerComponents().inject(this);
    }

    @Override
    public Observable<List<CurrencyTypeModel>> getCurrencyTypesObservable() {
        return dataProvider.getCurrencyTypesObservable();
    }

    @Override
    public Observable<List<CargoModel>> getCargosObservable() {
        return dataProvider.getCargoPageObservable().flatMap(new Func1<CargoPageResponse, Observable<List<CargoModel>>>() {
            @Override
            public Observable<List<CargoModel>> call(CargoPageResponse cargoPageResponse) {
                final List<CargoModel> result = new ArrayList<>();
                for (final CargoPageResponse.Load item : cargoPageResponse.getLoads()) {
                    result.add(CargoModel.fromResponseItem(item));
                }
                return Observable.from(result).toList();
            }
        });
    }

    @Override
    public Observable<Void> writeCurrencyTypesToDb(final List<CurrencyTypeModel> models) {
        return sqlOrmManager.writeCurrencyTypes(models);
    }

    @Override
    public Observable<Void> writeCargosToDb(List<CargoModel> models) {
        return sqlOrmManager.writeCargosToDb(models);
    }

    @Override
    public Observable<List<CargoModel>> loadCargosFromDb() {
        return sqlOrmManager.loadCargosFromDb();
    }
}
