package com.sobolgmail.i.stanislav.testati.dataprovider.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;
import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CurrencyTypeDao extends BaseDaoImpl<CurrencyTypeModel, Object> {

    public CurrencyTypeDao(ConnectionSource connectionSource, Class<CurrencyTypeModel> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public Observable<Void> write(final List<CurrencyTypeModel> models) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    delete(models);
                    for (final CurrencyTypeModel item : models) {
                        create(item);
                    }
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
