package com.sobolgmail.i.stanislav.testati.mpv;

import com.sobolgmail.i.stanislav.testati.utils.Logger;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    private IBaseView iBaseView;

    @Override
    public void bind(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
        Logger.write("bind");
    }

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
