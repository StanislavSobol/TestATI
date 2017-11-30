package com.sobolgmail.i.stanislav.testati.dataprovider.db;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.dataprovider.db.dao.CargoDao;
import com.sobolgmail.i.stanislav.testati.dataprovider.db.dao.CurrencyTypeDao;
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

public class SqlOrmManager extends OrmLiteSqliteOpenHelper implements ISqlOrmManager {

    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;
    private CurrencyTypeDao currencyTypeDao;
    private CargoDao cargoDao;


    public SqlOrmManager() {
        super(MApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
        try {
            currencyTypeDao = new CurrencyTypeDao(connectionSource, CurrencyTypeModel.class);
            cargoDao = new CargoDao(connectionSource, CargoModel.class);
        } catch (SQLException e) {
            Logger.writeError(e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, CurrencyTypeModel.class);
            TableUtils.createTable(connectionSource, CargoModel.class);
        } catch (SQLException e) {
            Logger.writeError(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // no migration
        try {
            TableUtils.dropTable(connectionSource, CurrencyTypeModel.class, true);
            TableUtils.dropTable(connectionSource, CargoModel.class, true);

            TableUtils.createTable(connectionSource, CurrencyTypeModel.class);
            TableUtils.createTable(connectionSource, CargoModel.class);
        } catch (SQLException e) {
            Logger.writeError(e);
        }
    }

    @Override
    public Observable<Void> writeCurrencyTypes(List<CurrencyTypeModel> models) {
            return currencyTypeDao.write(models);
    }
}
