package com.sobolgmail.i.stanislav.testati.interactor;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.CargoPageEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

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
    public Observable<CargoPageEntity> getCargosObservable() {
        return dataProvider.getCargosObservable();
    }
}
