package com.sobolgmail.i.stanislav.testati.mpv;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public interface IBasePresenter<T extends IBaseView> {

    void bind(final IBaseView iBaseView);

    void unbind();

    void destroy();

    void setChangingConfiguration(boolean value);
}
