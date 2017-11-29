package com.sobolgmail.i.stanislav.testati.di;

import com.sobolgmail.i.stanislav.testati.interactor.cargos.CargoInteractor;
import com.sobolgmail.i.stanislav.testati.interactor.cargos.ICargoInteractor;

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
    ICargoInteractor providesCargoInteractor() {
        return new CargoInteractor();
    }
}
