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

package org.dalol.presenter.services.pictures;

import org.dalol.mezmurmedia.mvp.model.pictures.PictureResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public interface PicturesApi {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("/filippella/b43a9608301fbf585ade1b331549bebf/raw/8ca3b865015ac20976fc0c965a89baca63a602e6/cakes.json")
    Call<PictureResponse> getPictures();
}
