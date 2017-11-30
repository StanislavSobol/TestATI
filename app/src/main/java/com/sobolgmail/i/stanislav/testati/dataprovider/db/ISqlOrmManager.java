package com.sobolgmail.i.stanislav.testati.dataprovider.db;

import com.sobolgmail.i.stanislav.testati.entity.model.CurrencyTypeModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface ISqlOrmManager {

    Observable<Void> writeCurrencyTypes(List<CurrencyTypeModel> models);
}
