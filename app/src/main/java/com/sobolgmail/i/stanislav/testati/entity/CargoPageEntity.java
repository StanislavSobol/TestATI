package com.sobolgmail.i.stanislav.testati.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargoPageEntity implements Serializable {

    @SerializedName("totalItems")
    private Integer totalItems;

    @SerializedName("itemsPerPage")
    private Integer itemsPerPage;

    @SerializedName("loads")
    private ArrayList<Load> loads;

    private static class Load {
        @SerializedName("id")
        private String id;

        @SerializedName("load")
        private MLoad load;

        private static class MLoad {
            @SerializedName("cargoType")
            private String cargoType;
        }
    }


//    LoadEntity loadEntity;

//    @SerializedName("truck")
//    Object truck;


//    private Load load;// = new Load();
//
//    public class Load {
//        @SerializedName("cargoType")
//        @Expose()
//        private String cargoType;
//
//    }
}
