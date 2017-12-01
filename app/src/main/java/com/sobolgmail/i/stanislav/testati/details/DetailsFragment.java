package com.sobolgmail.i.stanislav.testati.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sobolgmail.i.stanislav.testati.R;
import com.sobolgmail.i.stanislav.testati.entity.viewmodel.DetailsViewModel;
import com.sobolgmail.i.stanislav.testati.mpv.BaseFragment;

import butterknife.BindView;

/**
 * Created by Stanislav Sobol on 01.12.2017.
 * stanislav.i.sobol@gmail.com
 */

public class DetailsFragment extends BaseFragment<DetailsContract.IPresenter> implements DetailsContract.IView {

    private static final String ID_KEY = "ID_KEY";
    private String id;

    public static DetailsFragment newInstance(final String id) {
        final DetailsFragment fragment = new DetailsFragment();
        final Bundle args = new Bundle();
        args.putString(ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.fragment_detail_cargo_type_text_view)
    TextView cargoTypeTextView;

    @BindView(R.id.fragment_detail_cargo_load_text_view)
    TextView loadTextView;

    @BindView(R.id.fragment_detail_cargo_unload_text_view)
    TextView unloadTextView;

    @BindView(R.id.fragment_detail_note_text_view)
    TextView noteTextView;

    @BindView(R.id.fragment_detail_currency_type_text_view)
    TextView currencyTypeTextView;

    @Override
    protected DetailsContract.IPresenter createPresenter() {
        return new DetailsPresenter(id);
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ID_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void setViewModel(final DetailsViewModel detailsViewModel) {
        cargoTypeTextView.setText(detailsViewModel.getCargoType());
        loadTextView.setText(detailsViewModel.getLoadingCity());
        unloadTextView.setText(detailsViewModel.getUnloadingCity());
        noteTextView.setText(detailsViewModel.getNote());
        currencyTypeTextView.setText(detailsViewModel.getCurrencyTypeName());
    }
}
