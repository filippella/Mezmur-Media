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

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
@Module
public class ApplicationModule {

    private Context mAppContext;
    private final String mAccountType;

    public ApplicationModule(Context appContext, String accountType) {
        mAppContext = appContext;
        mAccountType = accountType;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return mAppContext.getSharedPreferences(mAccountType, Context.MODE_PRIVATE);
    }
}
