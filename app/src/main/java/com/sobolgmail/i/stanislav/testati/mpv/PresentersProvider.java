package com.sobolgmail.i.stanislav.testati.mpv;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class PresentersProvider {

    private static Map<UUID, IBasePresenter> presenters = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends IBasePresenter> T get(final IBaseView iBaseView) {
        return (T) presenters.get(iBaseView.getViewUuid());
    }

    public static <T extends IBasePresenter> void put(final IBaseView iBaseView, final T iBasePresenter) {
        presenters.put(iBaseView.getViewUuid(), iBasePresenter);
    }

    public static void remove(final IBaseView iBaseView) {
        presenters.remove(iBaseView.getViewUuid());
    }
}
