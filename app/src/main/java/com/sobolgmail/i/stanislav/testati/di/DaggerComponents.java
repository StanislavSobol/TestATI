package com.sobolgmail.i.stanislav.testati.di;

import com.sobolgmail.i.stanislav.testati.MApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Singleton
@Component(modules = {DaggerModules.class})
public interface DaggerComponents {

    void inject(MApplication mApplication);
}
