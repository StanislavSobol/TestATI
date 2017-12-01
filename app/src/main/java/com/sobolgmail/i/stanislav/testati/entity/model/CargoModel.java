package com.sobolgmail.i.stanislav.testati.entity.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
@Getter
@DatabaseTable(tableName = CargoModel.TABLE)
public class CargoModel {

    public static final String TABLE = "cargos";
    private static final String FIELD_ID = "id";
    private static final String FIELD_CARGO_TYPE = "cargo_type";
    private static final String FIELD_LOADING_CITY = "loading_city";
    private static final String FIELD_UNLOADING_CITY = "unloading_city";
    private static final String FIELD_NOTE = "note";
    private static final String FIELD_CURRENCY_ID = "currency_id";

    @DatabaseField(id = true, columnName = FIELD_ID, dataType = DataType.STRING)
    private String id;

    @DatabaseField(columnName = FIELD_CARGO_TYPE, dataType = DataType.STRING)
    private String cargoType;

    @DatabaseField(columnName = FIELD_LOADING_CITY, dataType = DataType.STRING)
    private String loadingCity;

    @DatabaseField(columnName = FIELD_UNLOADING_CITY, dataType = DataType.STRING)
    private String unloadingCity;

    @DatabaseField(columnName = FIELD_NOTE, dataType = DataType.STRING)
    private String note;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private CurrencyTypeModel currencyTypeModel = new CurrencyTypeModel();

    public static CargoModel fromResponseItem(CargoPageResponse.Load responseLoaditem) {
        final CargoModel result = new CargoModel();
        result.setId(responseLoaditem.getId());
        result.setCargoType(responseLoaditem.getLoad().getCargoType());
        result.setLoadingCity(responseLoaditem.getLoading().getLocation().getCity());
        result.setUnloadingCity(responseLoaditem.getUnloading().getLocation().getCity());
        result.setNote(responseLoaditem.getNote());
        // result.setCurrencyId(responseLoaditem.getRate().getCurrencyId());
        result.getCurrencyTypeModel().setId(responseLoaditem.getRate().getCurrencyId());
        return result;
    }
}
