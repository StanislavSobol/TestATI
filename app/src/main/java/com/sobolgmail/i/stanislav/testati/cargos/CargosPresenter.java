package com.sobolgmail.i.stanislav.testati.cargos;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.entity.CargoEntity;
import com.sobolgmail.i.stanislav.testati.entity.CurrencyTypeEntity;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;
import com.sobolgmail.i.stanislav.testati.interactor.IInteractor;
import com.sobolgmail.i.stanislav.testati.mpv.BasePresenter;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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


//        compositeSubscription.add(interactor.getCargosObservable()
//                //     .timeout(1, TimeUnit.MILLISECONDS)
//                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<CargoEntity>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.writeError(e);
//                    }
//
//                    @Override
//                    public void onNext(List<CargoEntity> list) {
//                        Logger.write(null);
//                    }
//                })
//        );
    }

    @Override
    protected void onBindWhenChangingConfiguration() {
        loadCargosFromDb();
    }

    private void loadCargos() {
        compositeSubscription.add(interactor.getCargosObservable()
                .timeout(300, TimeUnit.SECONDS)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable t) {
                        Logger.writeError(t);
                        if (t instanceof TimeoutException) {
                            Logger.write("TimeoutException");
                        }
                        loadCargosFromDb();
                    }
                })
                .doOnNext(new Action1<List<CargoEntity>>() {
                    @Override
                    public void call(List<CargoEntity> cargoEntities) {
                        Logger.write("");
                    }
                })
                .map(new Func1<List<CargoEntity>, List<CargoViewModel>>() {
                    @Override
                    public List<CargoViewModel> call(List<CargoEntity> cargoEntities) {
                        final List<CargoViewModel> result = new ArrayList<>();
                        for (final CargoEntity item : cargoEntities) {
                            result.add(CargoViewModel.fromModel(item));
                        }
                        return result;
                    }
                })
                .doOnNext(new Action1<List<CargoViewModel>>() {
                    @Override
                    public void call(List<CargoViewModel> cargoBiewModels) {
                        Logger.write("");
                    }
                })
                .subscribe()
        );



//        compositeSubscription.add(interactor.getCargosObservable()
//                .timeout(30, TimeUnit.SECONDS)
//                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<CargoEntity>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.writeError(e);
//                    }
//
//                    @Override
//                    public void onNext(List<CargoEntity> list) {
//                        Logger.write(null);
//                    }
//                })
//        );
    }

    private void loadCargosFromDb() {

    }

    private void loadCurrencyTypesFromDb() {

    }

    @Override
    protected void onBindWhenNotChangingConfiguration() {
        compositeSubscription.add(
                interactor.getCurrencyTypesObservable()
                        .timeout(30, TimeUnit.SECONDS)
                        .onBackpressureBuffer()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                Logger.writeError(t);
                                if (t instanceof TimeoutException) {
                                    Logger.write("TimeoutException");
                                }
                            }
                        })
                        .doOnNext(new Action1<List<CurrencyTypeEntity>>() {
                            @Override
                            public void call(List<CurrencyTypeEntity> currencyTypeEntities) {
                                interactor.writeCurrencyTypesToDb(currencyTypeEntities);
                                loadCargos();
                            }
                        })
                        .subscribe()
        );
    }

}
