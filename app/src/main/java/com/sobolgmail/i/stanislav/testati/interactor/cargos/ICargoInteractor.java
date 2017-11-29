package com.sobolgmail.i.stanislav.testati.interactor.cargos;

import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface ICargoInteractor {
    Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable();
}
