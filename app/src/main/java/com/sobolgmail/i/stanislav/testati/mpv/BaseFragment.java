package com.sobolgmail.i.stanislav.testati.mpv;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sobolgmail.i.stanislav.testati.utils.Logger;

import java.util.UUID;

import butterknife.ButterKnife;
import lombok.Getter;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

abstract public class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {

    private static final String EXTRA_VIEW_UUID = "EXTRA_VIEW_UUID";
    @Getter
    private T presenter;
    @Getter
    private UUID viewUuid;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            Logger.write("remove ");
            getPresenter().destroy();
            PresentersProvider.remove(this);
        }
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bind(this);
    }

    @Override
    public void onStop() {
        presenter.unbind();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_VIEW_UUID, viewUuid.toString());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            viewUuid = UUID.fromString(savedInstanceState.getString(EXTRA_VIEW_UUID));
        } else {
            viewUuid = UUID.randomUUID();
        }

        Logger.write("get");
        presenter = PresentersProvider.get(this);
        if (presenter == null) {
            Logger.write("NEW! ");
            PresentersProvider.put(this, presenter = createPresenter());
        }
        initViews();
    }

    protected abstract void initViews();
}
