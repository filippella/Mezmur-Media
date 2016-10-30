/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.presenter.business.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.dalol.model.services.ApiType;
import org.dalol.presenter.presentation.base.BaseView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import dagger.Lazy;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 *
 * BasePresenter.java is the base class for the presenters, V is the view and R is the response object
 */
public abstract class BasePresenter<V extends BaseView, R> implements Presenter {

    @NonNull @Inject protected Lazy<V> mView;
    private Map<ApiType, Call<?>> mApiCallQueue = new ConcurrentHashMap<>();

    @Override
    public void onCreate() {

    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void onDestroy() {

    }

    protected <F> void subscribe(Observable<F> observable, Observer<F> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
    }

    protected void enqueue(ApiType apiType, Call<R> call) {
        if(mApiCallQueue.containsKey(apiType)) {
            return;
        }
        mApiCallQueue.put(apiType, call);
        call.enqueue(new BaseApiCallback<R>(apiType, new BaseApiCallback.OnCallbackListener() {
            @Override
            public void onResponse(ApiType apiType, Call call, Response response) {
                mApiCallQueue.remove(apiType);
                if (response.isSuccessful()) {
                    onResponseRetrieved(true, call, response);
                } else {
                    onResponseRetrieved(false, call, response);
                }
            }

            @Override
            public void onFailure(ApiType apiType, Call call, Throwable throwable) {
                mApiCallQueue.remove(apiType);
                onFailureRetrieved(call, throwable);
            }
        }));
    }

    protected void cancelCall(ApiType apiType) {
        Call<?> call = mApiCallQueue.get(apiType);
        if (call != null) {
            call.cancel();
        }
    }

    protected void onResponseRetrieved(boolean successful, Call<R> call, Response<R> response) {
        //handled by child or consumed
    }

    protected void onFailureRetrieved(Call<R> call, Throwable throwable) {
        //handled by child or consumed
    }

    @Nullable public V getView() {
        Lazy<V> view = this.mView;
        if (view != null) {
            return view.get();
        }
        return null;
    }
}
