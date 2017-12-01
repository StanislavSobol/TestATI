package com.sobolgmail.i.stanislav.testati.details;

import com.sobolgmail.i.stanislav.testati.entity.viewmodel.DetailsViewModel;
import com.sobolgmail.i.stanislav.testati.mpv.IBasePresenter;
import com.sobolgmail.i.stanislav.testati.mpv.IBaseView;

/**
 * Created by Stanislav Sobol on 01.12.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface DetailsContract {
    interface IPresenter extends IBasePresenter {
    }

    interface IView extends IBaseView {
        void setViewModel(DetailsViewModel detailsViewModel);
    }
}
