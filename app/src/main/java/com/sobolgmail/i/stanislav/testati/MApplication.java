package com.sobolgmail.i.stanislav.testati;

import android.app.Application;

import com.sobolgmail.i.stanislav.testati.di.DaggerComponents;
import com.sobolgmail.i.stanislav.testati.di.DaggerDaggerComponents;
import com.sobolgmail.i.stanislav.testati.di.DaggerModules;

public class MApplication extends Application {

    private static MApplication instance;

    private DaggerComponents dagger2Components;

    public static DaggerComponents getDaggerComponents() {
        return instance.dagger2Components;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        dagger2Components =  DaggerDaggerComponents.builder().daggerModules(new DaggerModules()).build();
       // dagger2Components.inject(this);
    }
}
