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

import org.dalol.mezmurmedia.mvp.model.services.ApiType;
import org.dalol.model.callback.OnCallbackListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public class BaseApiCallback<T> implements Callback<T> {

    private final OnCallbackListener mCallbackListener;
    private final ApiType mApiType;

    public BaseApiCallback(ApiType apiType, OnCallbackListener callbackListener) {
        mApiType = apiType;
        mCallbackListener = callbackListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        mCallbackListener.onResponse(mApiType, call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        mCallbackListener.onFailure(mApiType, call, throwable);
    }
}
