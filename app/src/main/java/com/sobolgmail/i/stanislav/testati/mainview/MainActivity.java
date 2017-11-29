package com.sobolgmail.i.stanislav.testati.mainview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sobolgmail.i.stanislav.testati.R;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private static final String CARGO_FRAGMENT_TAG = "CARGO_FRAGMENT_TAG";
    private CargosFragment cargosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragments(savedInstanceState != null);
    }

    private void setFragments(final boolean changingConf) {
        if (changingConf) {
            cargosFragment = (CargosFragment) getFragmentManager().findFragmentByTag(CARGO_FRAGMENT_TAG);
        } else {
            cargosFragment = CargosFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_cargos_fragment_place_holder, cargosFragment, CARGO_FRAGMENT_TAG)
                    .commit();
        }
    }
}
