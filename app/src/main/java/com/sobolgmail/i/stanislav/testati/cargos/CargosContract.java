package com.sobolgmail.i.stanislav.testati.cargos;

import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;
import com.sobolgmail.i.stanislav.testati.mpv.IBasePresenter;
import com.sobolgmail.i.stanislav.testati.mpv.IBaseView;

import java.util.List;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface CargosContract {
    interface IPresenter extends IBasePresenter {
    }

    interface IView extends IBaseView {
        void setCargoViewModels(List<CargoViewModel> cargoViewModels);
    }
}
