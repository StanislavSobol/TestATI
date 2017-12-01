package com.sobolgmail.i.stanislav.testati.cargos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sobolgmail.i.stanislav.testati.R;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;
import com.sobolgmail.i.stanislav.testati.mpv.BaseFragment;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class CargosFragment extends BaseFragment<CargosContract.IPresenter> implements CargosContract.IView {

    private CargosAdapter adapter;

    public static CargosFragment newInstance() {
       return new CargosFragment();
    }

    @BindView(R.id.fragment_cargos_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected CargosContract.IPresenter createPresenter() {
        return new CargosPresenter();
    }

    @Override
    protected void initViews() {
        // only for debug purpose - shows the DB structure
        // startActivity(new Intent(CargosFragment.this.getActivity(), AndroidDatabaseManagerActivity.class));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter = new CargosAdapter());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cargos, container, false);
        return view;
    }

    @Override
    public void setCargoViewModels(List<CargoViewModel> cargoViewModels) {
        adapter.setItems(cargoViewModels);
    }
}
