package org.dalol.presenter.business.mezmur;

import org.dalol.model.mezmur.Mezmur;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.data.mezmur.MezmurListProvider;
import org.dalol.presenter.presentation.mezmur.MezmurListView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public class MezmurListPresenter extends BasePresenter<MezmurListView, Void> implements Observer<List<Mezmur>> {

    @Inject protected MezmurListProvider mMezmurListProvider;

    @Inject
    public MezmurListPresenter() {
    }

    @Override
    public void onViewReady() {
        super.onViewReady();
        getView().onShowDialog("Fetching mezmur list...");
        subscribe(mMezmurListProvider.getObservable(), this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
    }

    @Override
    public void onNext(List<Mezmur> mezmurs) {
        getView().onPopulateMezmurList(mezmurs);
        getView().onHideDialog();
    }
}
