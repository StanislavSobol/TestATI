package com.sobolgmail.i.stanislav.testati.interactor.cargos;

import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.retrofit.NetworkManager;

import java.util.List;

import rx.Observable;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargoInteractor implements ICargoInteractor {

    public CargoInteractor() {
       // MApplication.getDaggerComponents().inject(this);
    }

    @Override
    public Observable<List<CurrencyTypeEntity>> getCurrencyTypesObservable() {
        return NetworkManager.getService().getCurrencyTypesObservable();
    }
}
