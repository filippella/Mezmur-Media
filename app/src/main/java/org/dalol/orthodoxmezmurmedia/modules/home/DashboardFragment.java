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

package org.dalol.orthodoxmezmurmedia.modules.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.DashboardCategoryListAdapter;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerMezmurCategoryComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.MezmurCategoryModule;
import org.dalol.orthodoxmezmurmedia.utilities.custom.RecyclerListItemMarginDecorator;
import org.dalol.presenter.business.dashboard.DashboardFragmentPresenter;
import org.dalol.presenter.presentation.dashboard.DashboardFragmentView;

import java.util.List;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class DashboardFragment extends BaseFragment<DashboardFragmentPresenter> implements DashboardFragmentView {

    @BindView(R.id.recycler_view_List) protected RecyclerView mDashboardList;
    private DashboardCategoryListAdapter mAdapter;

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            //MezmurDashboardActivity activity = (MezmurDashboardActivity) context;
        }
    }

    @Override
    protected void resolveDependencies() {
        DaggerMezmurCategoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mezmurCategoryModule(new MezmurCategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        getPresenter().onViewReady();
    }

    @Override
    protected void bindView(View view) {
        mDashboardList.setHasFixedSize(true);
        mDashboardList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mDashboardList.addItemDecoration(new RecyclerListItemMarginDecorator(getResources().getDimensionPixelSize(R.dimen.mezmur_dashboard_list_item_margin_size)));
        mAdapter = new DashboardCategoryListAdapter(getLayoutInflater(getArguments()));
        mDashboardList.setAdapter(mAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_layout;
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onLoadCategories(List<MezmurListItem> mezmurCategories) {
        mAdapter.addCategories(mezmurCategories);
    }
}
