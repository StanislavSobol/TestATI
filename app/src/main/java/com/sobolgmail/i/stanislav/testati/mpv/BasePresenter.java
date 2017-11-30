package com.sobolgmail.i.stanislav.testati.mpv;

import com.sobolgmail.i.stanislav.testati.utils.Logger;

import lombok.Setter;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

abstract public class BasePresenter<T extends IBaseView> implements IBasePresenter {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    private IBaseView iBaseView;
    @Setter
    private boolean changingConfiguration;

    @Override
    public void bind(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
        if (changingConfiguration) {
            onBindWhenChangingConfiguration();
        } else {
            onBindWhenNotChangingConfiguration();
        }
        Logger.write("bind");
    }

    protected abstract void onBindWhenChangingConfiguration();

    protected abstract void onBindWhenNotChangingConfiguration();

    @Override
    public void unbind() {
        this.iBaseView = null;
        Logger.write("unbind");
    }

    @Override
    public void destroy() {
        compositeSubscription.unsubscribe();
        compositeSubscription.clear();
    }

    @SuppressWarnings("unchecked")
    public T getView() {
        return (T) iBaseView;
    }
}
