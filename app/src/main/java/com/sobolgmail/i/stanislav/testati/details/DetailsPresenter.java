package com.sobolgmail.i.stanislav.testati.details;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.DetailsViewModel;
import com.sobolgmail.i.stanislav.testati.interactor.IInteractor;
import com.sobolgmail.i.stanislav.testati.mpv.BasePresenter;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Stanislav Sobol on 01.12.2017.
 * stanislav.i.sobol@gmail.com
 */

public class DetailsPresenter  extends BasePresenter<DetailsContract.IView> implements DetailsContract.IPresenter {

    @Inject
    IInteractor interactor;

    private final String id;

    public DetailsPresenter(final String id) {
        MApplication.getDaggerComponents().inject(this);
        this.id = id;
    }

    @Override
    protected void onBindWhenChangingConfiguration() {
        loadCargoFromDb();
    }

    @Override
    protected void onBindWhenNotChangingConfiguration() {
        loadCargoFromDb();
    }

    private void loadCargoFromDb() {
        interactor.getCargoObservable(id)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CargoModel, DetailsViewModel>() {
                    @Override
                    public DetailsViewModel call(CargoModel cargoModel) {
                        return DetailsViewModel.fromModel(cargoModel);
                    }
                })
                .subscribe(new Subscriber<DetailsViewModel>() {
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
                    public void onNext(DetailsViewModel detailsViewModel) {
                        getView().setViewModel(detailsViewModel);
                    }
                });
    }
}
