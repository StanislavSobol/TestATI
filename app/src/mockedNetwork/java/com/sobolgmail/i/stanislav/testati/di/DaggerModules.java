package com.sobolgmail.i.stanislav.testati.di;

import com.sobolgmail.i.stanislav.testati.MockNetworkDataProvider;
import com.sobolgmail.i.stanislav.testati.dataprovider.IDataProvider;
import com.sobolgmail.i.stanislav.testati.dataprovider.retrofit.NetworkDataProvider;
import com.sobolgmail.i.stanislav.testati.interactor.IInteractor;
import com.sobolgmail.i.stanislav.testati.interactor.Interactor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

@Module
public class DaggerModules {
    @Provides
    @Singleton
    IInteractor providesCargoInteractor() {
        return new Interactor();
    }

    @Provides
    @Singleton
    IDataProvider providesDataProvider() {
        return new MockNetworkDataProvider();
    }
}
