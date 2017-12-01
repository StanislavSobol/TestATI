package com.sobolgmail.i.stanislav.testati.cargos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sobolgmail.i.stanislav.testati.MApplication;
import com.sobolgmail.i.stanislav.testati.R;
import com.sobolgmail.i.stanislav.testati.details.DetailsActivity;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.CargoViewModel;
import com.sobolgmail.i.stanislav.testati.mpv.BaseFragment;
import com.sobolgmail.i.stanislav.testati.utils.Logger;

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

    @BindView(R.id.full_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.fragment_cargos_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_cargos_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected CargosContract.IPresenter createPresenter() {
        return new CargosPresenter();
    }

    @Override
    protected void initViews() {
        setProgressBarVisible(true);
        // only for debug purpose - shows the DB structure
        // startActivity(new Intent(CargosFragment.this.getActivity(), AndroidDatabaseManagerActivity.class));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter = new CargosAdapter());

        adapter.setOnItemClickListener(new CargosAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String id) {
                Logger.write(id);
                DetailsActivity.startActivity(getActivity(), id);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().swipeRefreshLayoutRefreshed();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cargos, container, false);
    }

    @Override
    public void setCargoViewModels(List<CargoViewModel> cargoViewModels) {
        adapter.setItems(cargoViewModels);
        setProgressBarVisible(false);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showToastWithError(Throwable t) {
        String s = getResources().getString(R.string.error_http_beg);
        s += "\n";
        s += t.toString();
        s += "\n";
        s += getResources().getString(R.string.error_http_end);

        for (int i = 0; i < 3; i++) {
            Toast.makeText(MApplication.getAppContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showInfoAboutEmptyData() {
        final String s = getResources().getString(R.string.empty_db);
        Toast.makeText(MApplication.getAppContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void setProgressBarVisible(final boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
