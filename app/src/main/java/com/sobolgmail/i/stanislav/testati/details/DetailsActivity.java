package com.sobolgmail.i.stanislav.testati.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sobolgmail.i.stanislav.testati.R;

/**
 * Created by Stanislav Sobol on 01.12.2017.
 * stanislav.i.sobol@gmail.com
 */

public class DetailsActivity extends AppCompatActivity {

    private static final String DETAILS_FRAGMENT_TAG = "DETAILS_FRAGMENT_TAG";
    private static final String EXTRA_ID = "EXTRA_CHAT";

    private DetailsFragment detailsFragment;
    private String id;

    public static void startActivity(final Context context, final String id) {
        final Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBarHomeButton();

        id = getIntent().getStringExtra(EXTRA_ID);

        setContentView(R.layout.activity_details);
        setFragments(savedInstanceState != null);
    }

    private void setFragments(final boolean changingConf) {
        if (changingConf) {
            detailsFragment = (DetailsFragment) getFragmentManager().findFragmentByTag(DETAILS_FRAGMENT_TAG);
        } else {
            detailsFragment = DetailsFragment.newInstance(id);
            getFragmentManager().beginTransaction()
                    .replace(R.id.activity_details_fragment_place_holder, detailsFragment, DETAILS_FRAGMENT_TAG)
                    .commit();
        }
    }

    protected void setupActionBarHomeButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}
