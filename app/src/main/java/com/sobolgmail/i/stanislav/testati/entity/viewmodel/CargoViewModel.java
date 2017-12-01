package com.sobolgmail.i.stanislav.testati.entity.viewmodel;

import com.sobolgmail.i.stanislav.testati.entity.model.CargoModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
@Getter
public class CargoViewModel {
    private String id;
    private String cargoType;
    private String loadingCity;
    private String unloadingCity;

    public static List<CargoViewModel> fromModelsList(final List<CargoModel> cargoModels) {
        final List<CargoViewModel> result = new ArrayList<>();
        for (final CargoModel item : cargoModels) {
            result.add(CargoViewModel.fromModel(item));
        }
        return result;
    }

    private static CargoViewModel fromModel(final CargoModel item) {
        final CargoViewModel result = new CargoViewModel();
        result.setId(item.getId());
        result.setCargoType(item.getCargoType());
        result.setLoadingCity(item.getLoadingCity());
        result.setUnloadingCity(item.getUnloadingCity());
        return result;
    }
}
