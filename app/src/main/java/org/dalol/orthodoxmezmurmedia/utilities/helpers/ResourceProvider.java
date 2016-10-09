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

package org.dalol.orthodoxmezmurmedia.utilities.helpers;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import org.dalol.orthodoxmezmurmedia.R;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/10/2016
 */
public class ResourceProvider {

    @Inject protected Context nContext;

    @Inject
    public ResourceProvider() {
    }

    @DrawableRes public int getGridMenuIcon() {
        return R.mipmap.ic_dashboard_white_24dp;
    }

    @DrawableRes public int getListMenuIcon() {
        return R.mipmap.ic_view_list_white_24dp;
    }

    @StringRes public int getHomeTitle() {
        return R.string.activity_dashboard_title_string;
    }

    public String getRecentMezmurTitle() {
        return nContext.getString(R.string.recent_mezmur_title);
    }

    public String getDashboardViewTitle() {
        return nContext.getString(R.string.dashboard_content_title);
    }
}
