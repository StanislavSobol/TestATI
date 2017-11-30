package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.CargoEntity;
import com.sobolgmail.i.stanislav.testati.entity.CargoPageEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

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
    public Observable<CargoPageEntity> getCargoPageObservable() {
        return dataProvider.getCargoPageObservable();
    }

    @Override
    public Observable<List<CargoEntity>> getCargosObservable() {
        return dataProvider.getCargoPageObservable().flatMap(new Func1<CargoPageEntity, Observable<List<CargoEntity>>>() {
            @Override
            public Observable<List<CargoEntity>> call(CargoPageEntity cargoPageEntity) {
                final List<CargoEntity> result = new ArrayList<>();

                for (final CargoPageEntity.Load item : cargoPageEntity.getLoads()) {
                    final CargoEntity cargoEntity = new CargoEntity();
                    cargoEntity.setId(item.getId());
                    cargoEntity.setCargoType(item.getLoad().getCargoType());
                    cargoEntity.setCity(item.getUnloading().getLocation().getCity());
                    cargoEntity.setNote(item.getNote());
                    cargoEntity.setCurrencyId(item.getRate().getCurrencyId());
                    result.add(cargoEntity);
                }

                return Observable.from(result).toList();
            }
        });
    }
}
