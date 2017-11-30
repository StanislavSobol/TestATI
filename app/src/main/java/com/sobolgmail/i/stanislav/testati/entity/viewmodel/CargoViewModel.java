package com.sobolgmail.i.stanislav.testati.entity.viewmodel;

import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;

import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
public class CargoViewModel {
    private String id;
    private String cargoType;
    private String loadingCity;
    private String unloadingCity;
    private String currencyTypeName;

    public static CargoViewModel fromModel(CargoModel item) {
        final CargoViewModel result = new CargoViewModel();
        result.setId(item.getId());
        result.setCargoType(item.getCargoType());
        result.setLoadingCity(item.getLoadingCity());
        result.setUnloadingCity(item.getUnloadingCity());
        return result;
    }
}
