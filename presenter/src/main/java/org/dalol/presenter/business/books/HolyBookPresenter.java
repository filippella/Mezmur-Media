package org.dalol.presenter.business.books;

import org.dalol.model.books.HolyContentBook;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.data.books.HolyBooksContentProvider;
import org.dalol.presenter.presentation.books.HolyBookView;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyBookPresenter extends BasePresenter<HolyBookView, Void> implements Observer<HolyContentBook> {

    @Inject protected HolyBooksContentProvider mContentProvider;

    @Inject
    public HolyBookPresenter() {
    }

    @Override
    public void onViewReady() {
        super.onViewReady();
        getView().onShowDialog("Fetching holy content...");
        subscribe(mContentProvider.getObservable(), this);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        getView().onShowToast(e.getMessage());
    }

    @Override
    public void onNext(HolyContentBook holyContentBook) {
        getView().onLoadContent(holyContentBook);
    }
}
