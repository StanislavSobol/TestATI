package com.sobolgmail.i.stanislav.testati.entity.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
@Getter
@DatabaseTable(tableName = CurrencyTypeModel.TABLE)
public class CurrencyTypeModel {

    public static final String TABLE = "currency_types";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ID2 = "id2";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_NAME_ENG = "name_eng";
    private static final String FIELD_MODIFIER = "modifier";
    private static final String CURRENCY_ID_PER_KM = "сurrency_id_per_km";

    @DatabaseField(id = true, columnName = FIELD_ID, dataType = DataType.INTEGER)
    private int id;

    @DatabaseField(columnName = FIELD_ID2, dataType = DataType.STRING)
    private String id2;

    @DatabaseField(columnName = FIELD_NAME, dataType = DataType.STRING)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_ENG, dataType = DataType.STRING)
    private String nameEng;

    @DatabaseField(columnName = FIELD_MODIFIER, dataType = DataType.STRING)
    private String modifier;

    @DatabaseField(columnName = CURRENCY_ID_PER_KM, dataType = DataType.INTEGER)
    private int сurrencyIdPerKm;

    public static CurrencyTypeModel fromResponseItem(final CurrencyTypeResponse responseItem) {
        final CurrencyTypeModel result = new CurrencyTypeModel();
        result.setId(responseItem.getId());
        result.setId2(responseItem.getId2());
        result.setName(responseItem.getName());
        result.setNameEng(responseItem.getNameEng());
        result.setModifier(responseItem.getModifier());
        result.setСurrencyIdPerKm(responseItem.getСurrencyIdPerKm());
        return result;
    }

}
