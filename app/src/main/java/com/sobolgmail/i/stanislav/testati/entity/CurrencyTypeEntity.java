package com.sobolgmail.i.stanislav.testati.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CurrencyTypeEntity {
    @SerializedName("Name")
    String name;
    @SerializedName("NameEng")
    String nameEng;
    @SerializedName("Modifier")
    String modifier;
    @SerializedName("CurrencyIdPerKm")
    String сurrencyIdPerKm;
    @SerializedName("Id")
    String id;
    @SerializedName("Id2")
    String id2;



//    "Name": "kzt/т.",
//            "NameEng": "kzt/ton",
//            "Modifier": 1,
//            "CurrencyIdPerKm": null,
//            "Id": 25,
//            "Id2": "8628f49a-e0e6-e511-b00c-002590e45781"


}
