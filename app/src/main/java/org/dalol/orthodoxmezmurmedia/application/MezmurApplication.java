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

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.crashlog.CrashExceptionHandler;
import org.dalol.orthodoxmezmurmedia.basic.di.components.ApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ApplicationModule;
import org.dalol.orthodoxmezmurmedia.modules.splash.SplashActivity;

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
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if(activity.getClass().equals(SplashActivity.class)) {
                    // try without runOnUiThread if it will not help
                    initCrashHandler();
                    initializeApplicationComponentForDagger();
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
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
