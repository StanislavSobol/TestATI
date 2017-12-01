package com.sobolgmail.i.stanislav.testati.entity.viewmodel;

import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 01.12.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
@Getter
public class DetailsViewModel {
    private String cargoType;
    private String loadingCity;
    private String unloadingCity;
    private String note;
    private String currencyTypeName;

    public static DetailsViewModel fromModel(final CargoModel cargoModel) {
        final DetailsViewModel result = new DetailsViewModel();
        result.setCargoType(cargoModel.getCargoType());
        result.setLoadingCity(cargoModel.getLoadingCity());
        result.setUnloadingCity(cargoModel.getUnloadingCity());
        result.setNote(cargoModel.getNote());
        result.setCurrencyTypeName(cargoModel.getCurrencyTypeModel().getName() + "(" + cargoModel.getCurrencyTypeModel().getNameEng()  +")");
        return result;
    }
}
