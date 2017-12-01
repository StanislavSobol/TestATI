package com.sobolgmail.i.stanislav.testati;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

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

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    public static boolean isOnlineWithToast(boolean showToastIfNot) {
        final ConnectivityManager cm =
                (ConnectivityManager) instance.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
            return false ;
        }

        final NetworkInfo netInfo = cm.getActiveNetworkInfo();

        final boolean result = netInfo != null && netInfo.isConnectedOrConnecting();

        if (showToastIfNot && !result) {
            final String s = instance.getResources().getString(R.string.error_no_internet);
            Toast.makeText(instance, s, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
