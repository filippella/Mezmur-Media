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

package org.dalol.orthodoxmezmurmedia.business.di.modules;

import android.content.Context;

import org.dalol.orthodoxmezmurmedia.business.di.scopes.CustomScope;
import org.dalol.orthodoxmezmurmedia.mvp.view.dashboard.DashboardView;

import dagger.Module;
import dagger.Provides;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
@Module
public class DashboardModule {

    private DashboardView mDashboardView;

    public DashboardModule(DashboardView dashboardView) {
        mDashboardView = dashboardView;
    }

    @Provides
    @CustomScope
    DashboardView provideView() {
        return this.mDashboardView;
    }

    @Provides
    @CustomScope
    Context provideContext() {
        return this.mDashboardView.getApplicationContext();
    }
}
