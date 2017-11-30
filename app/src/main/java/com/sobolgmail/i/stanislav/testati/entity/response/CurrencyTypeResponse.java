package com.sobolgmail.i.stanislav.testati.entity.response;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Getter
public class CurrencyTypeResponse {

    @SerializedName("Name")
    private String name;

    @SerializedName("NameEng")
    private String nameEng;

    @SerializedName("Modifier")
    private String modifier;

    @SerializedName("CurrencyIdPerKm")
    private Integer сurrencyIdPerKm;

    @SerializedName("Id")
    private Integer id;

    @SerializedName("Id2")
    private String id2;

    public Integer getId() {
        return id == null ? 0 : id;
    }

    public Integer getСurrencyIdPerKm() {
        return сurrencyIdPerKm == null ? 0 : сurrencyIdPerKm;

    }
}
