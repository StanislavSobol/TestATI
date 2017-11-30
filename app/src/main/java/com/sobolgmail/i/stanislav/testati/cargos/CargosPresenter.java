package com.sobolgmail.i.stanislav.testati.cargos;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.entity.CargoEntity;
import com.sobolgmail.i.stanislav.testati.interactor.IInteractor;
import com.sobolgmail.i.stanislav.testati.mpv.BasePresenter;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargosPresenter extends BasePresenter<CargosContract.IView> implements CargosContract.IPresenter {

    @Inject
    IInteractor interactor;

    public CargosPresenter() {
        MApplication.getDaggerComponents().inject(this);


//        compositeSubscription.add(interactor.getCurrencyTypesObservable()
//                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<CurrencyTypeEntity>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.write(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<CurrencyTypeEntity> entities) {
//                        Logger.write(null);
//                    }
//                })
//        );

//        compositeSubscription.add(interactor.getCargoPageObservable()
//                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CargoPageEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.write(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CargoPageEntity entity) {
//                        Logger.write(null);
//                    }
//                })
//        );


        compositeSubscription.add(interactor.getCargosObservable()
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CargoEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.write(e.getMessage());
                    }

                    @Override
                    public void onNext(List<CargoEntity> list) {
                        Logger.write(null);
                    }
                })
        );
    }
}
