/*
 * Copyright (c) 2016 Orthodox Mezmur Media
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private List<NetworkChangeObserver> networkChangeObservers = new ArrayList<>();

    public void addNetworkChangeListener(NetworkChangeObserver networkChangeObserver) {
        this.networkChangeObservers.add(networkChangeObserver);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        for (NetworkChangeObserver networkChangeObserver : networkChangeObservers) {
            networkChangeObserver.onNetworkStateChange(activeNetworkInfo != null && activeNetworkInfo.isConnected());
        }
    }

    public void removeNetworkChangeListener(NetworkChangeObserver networkChangeObserver) {
        this.networkChangeObservers.remove(networkChangeObserver);
    }

    public interface NetworkChangeObserver {
        void onNetworkStateChange(boolean isConnected);
    }
}