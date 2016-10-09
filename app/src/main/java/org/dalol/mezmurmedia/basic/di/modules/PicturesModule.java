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

package org.dalol.mezmurmedia.basic.di.modules;

import org.dalol.mezmurmedia.basic.di.scopes.PerJourney;
import org.dalol.presenter.presentation.pictures.PicturesView;
import org.dalol.presenter.services.pictures.PicturesApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
@Module
public class PicturesModule {

    private PicturesView mPicturesView;

    public PicturesModule(PicturesView picturesView) {
        mPicturesView = picturesView;
    }

    @PerJourney
    @Provides
    PicturesView provideView() {
        return mPicturesView;
    }

    @PerJourney
    @Provides
    PicturesApi provideApiTask(Retrofit retrofit) {
        return retrofit.create(PicturesApi.class);
    }
}
