package com.sobolgmail.i.stanislav.testati.dataprovider.db;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

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

    @Override
    public Observable<Void> writeCargosToDb(List<CargoModel> models) {
        return cargoDao.write(models);
    }

    @Override
    public Observable<List<CargoModel>> loadCargosFromDb() {
        return cargoDao.loadAll();
    }

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }
}
