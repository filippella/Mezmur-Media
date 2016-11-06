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

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.di.components.ApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerApplicationComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ApplicationModule;
import org.dalol.orthodoxmezmurmedia.basic.receiver.NetworkChangeReceiver;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/27/2016
 */
public class MezmurApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static Context mInstanceContext;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstanceContext = this;
        initNetworkChangeNotifier();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initialize();
            }
        }).start();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(networkChangeReceiver);
    }

    private void initNetworkChangeNotifier() {
        IntentFilter networkFilter = new IntentFilter();
        networkFilter.addAction(getString(R.string.network_change_action));
        this.networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(this.networkChangeReceiver, networkFilter);
    }

    private void initCrashHandler() {
//        UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
//        // Register default exceptions handler.
//        Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler(exceptionHandler, this));
    }

    public void initialize() {
        initCrashHandler();
        initAppComponent();
    }

    private void initAppComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule("account-type"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getInstance() {
        return mInstanceContext;
    }

    public void registerForNetworkChange(NetworkChangeReceiver.NetworkChangeObserver observer) {
        networkChangeReceiver.addNetworkChangeListener(observer);
    }

    public void unregisterForNetworkChange(NetworkChangeReceiver.NetworkChangeObserver observer) {
        networkChangeReceiver.removeNetworkChangeListener(observer);
    }
}
