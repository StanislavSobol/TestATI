package com.sobolgmail.i.stanislav.testati.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Getter
public class CargoPageEntity {

    @SerializedName("totalItems")
    private Integer totalItems;

    @SerializedName("itemsPerPage")
    private Integer itemsPerPage;

    @SerializedName("loads")
    private ArrayList<Load> loads;

    @Getter
    public static class Load {
        @SerializedName("id")
        private String id;

        @SerializedName("load")
        private MLoad load;

        @Getter
        public static class MLoad {
            @SerializedName("cargoType")
            private String cargoType;
        }

        @SerializedName("unloading")
        private Unloading unloading;

        @Getter
        public static class Unloading {
            @SerializedName("location")
            Location location;

            @Getter
            public static class Location {
                @SerializedName("city")
                String city;
            }
        }

        @SerializedName("note")
        private String note;

        @SerializedName("rate")
        private Rate rate;

        @Getter
        public static class Rate {
            @SerializedName("currency")
            private Integer currencyId;
        }
    }


}
