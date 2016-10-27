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

package org.dalol.orthodoxmezmurmedia.application;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Application;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.crashlog.CrashExceptionHandler;
import org.dalol.orthodoxmezmurmedia.basic.di.components.ApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ApplicationModule;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/27/2016
 */
public class MezmurApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initCrashHandler();
        initializeApplicationComponentForDagger();
    }

    private void initCrashHandler() {
        UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // Register default exceptions handler.
        Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler(exceptionHandler, this));
    }

    private void initializeApplicationComponentForDagger() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, getResources().getString(R.string.account_type)))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
