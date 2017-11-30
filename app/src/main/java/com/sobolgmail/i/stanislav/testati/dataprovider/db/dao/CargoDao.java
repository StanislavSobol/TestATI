package com.sobolgmail.i.stanislav.testati.dataprovider.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;

import java.sql.SQLException;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargoDao extends BaseDaoImpl<CargoModel, Object> {

    public CargoDao(ConnectionSource connectionSource, Class<CargoModel> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
