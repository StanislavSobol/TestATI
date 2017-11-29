package com.sobolgmail.i.stanislav.testati.cargos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sobolgmail.i.stanislav.testati.R;
import com.sobolgmail.i.stanislav.testati.mpv.BaseFragment;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargosFragment extends BaseFragment<CargosContract.IPresenter> implements CargosContract.IView {

    public static CargosFragment newInstance() {
       return new CargosFragment();
    }

    @Override
    protected CargosContract.IPresenter createPresenter() {
        return new CargosPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dialogs, container, false);
        return view;
    }
}
