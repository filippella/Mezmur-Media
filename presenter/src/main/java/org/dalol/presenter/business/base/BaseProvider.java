package org.dalol.presenter.business.base;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public abstract class BaseProvider<T> implements Observable.OnSubscribe<T> {

    @Inject protected Context mContext;
    @Inject protected Gson mGson;

    protected String mFileName;
    protected Class<T> mClass;

    public Observable<T> getObservable() {
        return Observable.create(BaseProvider.this);
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            T work = doBackgroundWork();
            subscriber.onNext(work);
            subscriber.onCompleted();
        } catch(Exception e) {
            subscriber.onError(e);
        }
    }

    /**
     * This method is used to set the file name to be fetched from the assets directory
     *
     * @param fileName
     */
    public void init(Class<T> clazz, String fileName) {
        mClass = clazz;
        mFileName = fileName;
    }

    protected abstract T doBackgroundWork();
}
