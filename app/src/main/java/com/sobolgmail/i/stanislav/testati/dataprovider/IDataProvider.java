package com.sobolgmail.i.stanislav.testati.dataprovider;

import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IDataProvider {
    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();
}
