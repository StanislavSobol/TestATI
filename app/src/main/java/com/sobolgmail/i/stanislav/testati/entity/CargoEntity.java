package com.sobolgmail.i.stanislav.testati.entity;

import com.sobolgmail.i.stanislav.testati.entity.response.CargoPageResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Stanislav Sobol on 30.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Setter
@Getter
public class CargoEntity {
    private String id;
    private String cargoType;
    private String loadingCity;
    private String unloadingCity;
    private String note;
    private Integer currencyId;

    public static CargoEntity fromResponseItem(CargoPageResponse.Load responseLoaditem) {
        final CargoEntity result = new CargoEntity();
        result.setId(responseLoaditem.getId());
        result.setCargoType(responseLoaditem.getLoad().getCargoType());
        result.setLoadingCity(responseLoaditem.getLoading().getLocation().getCity());
        result.setUnloadingCity(responseLoaditem.getUnloading().getLocation().getCity());
        result.setNote(responseLoaditem.getNote());
        result.setCurrencyId(responseLoaditem.getRate().getCurrencyId());
        return result;
    }
}
