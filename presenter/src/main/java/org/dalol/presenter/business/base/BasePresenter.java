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

import org.dalol.mezmurmedia.mvp.model.callback.BaseApiCallback;
import org.dalol.mezmurmedia.mvp.model.callback.OnCallbackListener;
import org.dalol.mezmurmedia.mvp.model.services.ApiType;
import org.dalol.mezmurmedia.presentation.base.BaseView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class BasePresenter<V extends BaseView, R> {

    @NonNull @Inject protected V mView;
    private Map<ApiType, Call<?>> mApiCallQueue = new ConcurrentHashMap<>();

    public V getView() {
        return mView;
    }

    protected void enqueue(ApiType apiType, Call<R> call) {
        if(mApiCallQueue.containsKey(apiType)) {
            return;
        }
        mApiCallQueue.put(apiType, call);
        call.enqueue(new BaseApiCallback<R>(apiType, new OnCallbackListener() {
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

    public void onCreate() {

    }

    public void onDestroy() {

    }

    public void onViewReady() {

    }
}
