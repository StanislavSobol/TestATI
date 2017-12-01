package com.sobolgmail.i.stanislav.testati.cargos;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;
import com.sobolgmail.i.stanislav.testati.interactor.IInteractor;
import com.sobolgmail.i.stanislav.testati.mpv.BasePresenter;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

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
    }

    @Override
    protected void onBindWhenChangingConfiguration() {
        loadCargosFromDb();
    }

    @Override
    protected void onBindWhenNotChangingConfiguration() {
        loadCurrencyTypesFromNetwork();
    }

    private void loadCurrencyTypesFromNetwork() {

        compositeSubscription.add(
                interactor.getCurrencyTypesObservable()
                        .timeout(IDataProvider.NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
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
                        .doOnNext(new Action1<List<CurrencyTypeModel>>() {
                            @Override
                            public void call(final List<CurrencyTypeModel> currencyTypeModels) {
                                loadCargosFromNetwork(currencyTypeModels);
//                                interactor.writeCurrencyTypesToDb(currencyTypeModels)
//                                        .subscribeOn(Schedulers.computation())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Subscriber<Void>() {
//                                            @Override
//                                            public void onCompleted() {
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                Logger.writeError(e);
//                                            }
//
//                                            @Override
//                                            public void onNext(Void aVoid) {
//                                       //         loadCargosFromNetwork(currencyTypeModels);
//                                            }
//                                        });
                            }
                        })
                        .subscribe()
        );

//        compositeSubscription.add(
//                interactor.getCurrencyTypesObservable()
//                        .timeout(IDataProvider.NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
//                        .onBackpressureBuffer()
//                        .subscribeOn(Schedulers.computation())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnError(new Action1<Throwable>() {
//                            @Override
//                            public void call(Throwable t) {
//                                Logger.writeError(t);
//                                if (t instanceof TimeoutException) {
//                                    Logger.write("TimeoutException");
//                                }
//                            }
//                        })
//                        .doOnNext(new Action1<List<CurrencyTypeModel>>() {
//                            @Override
//                            public void call(List<CurrencyTypeModel> currencyTypeEntities) {
//                                interactor.writeCurrencyTypesToDb(currencyTypeEntities)
//                                        .subscribeOn(Schedulers.computation())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new Subscriber<Void>() {
//                                            @Override
//                                            public void onCompleted() {
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                Logger.writeError(e);
//                                            }
//
//                                            @Override
//                                            public void onNext(Void aVoid) {
//                                                loadCargosFromNetwork();
//                                            }
//                                        });
//                            }
//                        })
//                        .subscribe()
//        );
    }

    private void loadCargosFromNetwork(final List<CurrencyTypeModel> currencyTypeModels) {
        compositeSubscription.add(interactor.getCargosObservable()
                .timeout(IDataProvider.NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<List<CargoModel>>() {
                    @Override
                    public void call(List<CargoModel> cargoModels) {
                        for (final CargoModel cargo : cargoModels) {
                            for (final CurrencyTypeModel type : currencyTypeModels) {
                                if (type.getId() == cargo.getCurrencyTypeModel().getId()) {
                                    cargo.setCurrencyTypeModel(type);
                                    break;
                                }
                            }
                        }
                        writeCargosToDb(cargoModels, currencyTypeModels);
                    }
                })
                .map(new Func1<List<CargoModel>, List<CargoViewModel>>() {
                    @Override
                    public List<CargoViewModel> call(List<CargoModel> cargoModels) {
                        return CargoViewModel.fromModelsList(cargoModels);
                    }
                })
                .subscribe(new Subscriber<List<CargoViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.writeError(e);
                        if (e instanceof TimeoutException) {
                            Logger.write("TimeoutException");
                        }
                        loadCargosFromDb();
                    }

                    @Override
                    public void onNext(List<CargoViewModel> cargoViewModels) {
                        getView().setCargoViewModels(cargoViewModels);
                    }
                })
        );


//        compositeSubscription.add(interactor.getCargosObservable()
//                .timeout(IDataProvider.NETWORK_TIMEOUT_MS, TimeUnit.MILLISECONDS)
//                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError(new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable t) {
//                        Logger.writeError(t);
//                        if (t instanceof TimeoutException) {
//                            Logger.write("TimeoutException");
//                        }
//                        loadCargosFromDb();
//                    }
//                })
//                .doOnNext(new Action1<List<CargoModel>>() {
//                    @Override
//                    public void call(List<CargoModel> cargoModels) {
//                        for (final CargoModel cargo : cargoModels) {
//                            for (final CurrencyTypeModel type : currencyTypeModels) {
//                                if (type.getId() == cargo.getCurrencyTypeModel().getId()) {
//                                    cargo.setCurrencyTypeModel(type);
//                                    break;
//                                }
//                            }
//                        }
//                        writeCargosToDb(cargoModels, currencyTypeModels);
//                    }
//                })
//                .map(new Func1<List<CargoModel>, List<CargoViewModel>>() {
//                    @Override
//                    public List<CargoViewModel> call(List<CargoModel> cargoModels) {
//                        return CargoViewModel.fromModelsList(cargoModels);
//                    }
//                })
//                .doOnNext(new Action1<List<CargoViewModel>>() {
//                    @Override
//                    public void call(List<CargoViewModel> cargoViewModels) {
//                        getView().setCargoViewModels(cargoViewModels);
//                    }
//                })
//                .subscribe()
//        );
    }

    private void writeCargosToDb(final List<CargoModel> cargoModels, final List<CurrencyTypeModel> currencyTypeModels) {
        interactor.writeCurrencyTypesToDb(currencyTypeModels)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.writeError(e);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        interactor.writeCargosToDb(cargoModels)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<Void>() {
                                    @Override
                                    public void onCompleted() {
                                        unsubscribe();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Logger.writeError(e);
                                        unsubscribe();
                                    }

                                    @Override
                                    public void onNext(Void aVoid) {

                                    }
                                });
                    }
                });
    }

    private void loadCargosFromDb() {
        interactor.loadCargosFromDb()
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<CargoModel>, List<CargoViewModel>>() {
                    @Override
                    public List<CargoViewModel> call(List<CargoModel> cargoModels) {
                        return CargoViewModel.fromModelsList(cargoModels);
                    }
                })
                .subscribe(new Subscriber<List<CargoViewModel>>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.writeError(e);
                        unsubscribe();
                    }

                    @Override
                    public void onNext(List<CargoViewModel> cargoViewModels) {
                        getView().setCargoViewModels(cargoViewModels);
                    }
                });
    }
}
