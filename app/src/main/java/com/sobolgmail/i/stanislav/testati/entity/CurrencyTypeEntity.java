package com.sobolgmail.i.stanislav.testati.entity;

import com.google.gson.annotations.SerializedName;
import com.sobolgmail.i.stanislav.testati.entity.response.CurrencyTypeResponse;

import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
public class CurrencyTypeEntity {
    private int id;
    private String id2;
    private String name;
    private String nameEng;
    private String modifier;
    private int сurrencyIdPerKm;

    public static CurrencyTypeEntity fromResponseItem(final CurrencyTypeResponse responseItem) {
        final CurrencyTypeEntity result = new CurrencyTypeEntity();
        result.setId(responseItem.getId());
        result.setId2(responseItem.getId2());
        result.setName(responseItem.getName());
        result.setNameEng(responseItem.getNameEng());
        result.setModifier(responseItem.getModifier());
        result.setСurrencyIdPerKm(responseItem.getСurrencyIdPerKm());
        return result;
    }
}
