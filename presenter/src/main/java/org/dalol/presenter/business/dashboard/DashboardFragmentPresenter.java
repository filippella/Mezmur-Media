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

package org.dalol.presenter.business.dashboard;

import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.presentation.dashboard.DashboardFragmentView;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class DashboardFragmentPresenter extends BasePresenter<DashboardFragmentView, Void> {

    public static final int TYPE_VERTICAL_LIST = 0;
    public static final int TYPE_GRID_VIEW = 1;

    private int dashboardViewType = TYPE_GRID_VIEW;

//    @Inject protected ResourceProvider mResourceProvider;

    @Inject
    public DashboardFragmentPresenter() {
    }

    @Override
    public void onViewReady() {
        getView().onShowToast("Hello World from Filippo!");
    }

    public int getDashboardViewType() {
        return dashboardViewType;
    }

//    public int getActiveViewTypeMenuIcon() {
//        return getByDashboardViewType(dashboardViewType);
//    }

//    private int getByDashboardViewType(int dashboardViewType) {
//        switch (dashboardViewType) {
//            case TYPE_GRID_VIEW:
//                return mResourceProvider.getListMenuIcon();
//        }
//        return mResourceProvider.getGridMenuIcon();
//    }

    /**
     * This method is responsible for resolving the type of the menu to be displayed in the first fragment
     */
    public void handleViewTypeForRecyclerView() {
        int dashboardViewType = getDashboardViewType();
//        switch (dashboardViewType) {
//            case TYPE_GRID_VIEW:
//                getView().setUpGridDashboard();
//                break;
//            case TYPE_VERTICAL_LIST:
//                getView().setUpVerticalDashboard();
//                break;
//        }
    }

//    public int changeViewTypeIcon() {
//        if (dashboardViewType == TYPE_VERTICAL_LIST) {
//            return getByDashboardViewType(dashboardViewType = TYPE_GRID_VIEW);
//        } else {
//            return getByDashboardViewType(dashboardViewType = TYPE_VERTICAL_LIST);
//        }
//    }
}
