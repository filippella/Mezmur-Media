package org.dalol.presenter.business.base;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public abstract class BaseProvider<T> implements Observable.OnSubscribe<T> {

    public Observable<T> getObservable() {
        return Observable.create(BaseProvider.this);
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {

        T work = doBackgroundWork();

        subscriber.onNext(work);
        subscriber.onCompleted();
    }

    protected abstract T doBackgroundWork();
}
