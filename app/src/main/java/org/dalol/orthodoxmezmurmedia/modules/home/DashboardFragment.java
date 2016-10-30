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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.DashboardCategoryListAdapter;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerMezmurCategoryComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.MezmurCategoryModule;
import org.dalol.orthodoxmezmurmedia.modules.mezmur.MezmurSearchActivity;
import org.dalol.orthodoxmezmurmedia.utilities.custom.HidingScrollListener;
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

    @BindView(R.id.recycler_view_mezmur_category) protected RecyclerView mDashboardList;
    @BindView(R.id.search_fab) protected FloatingActionButton mFabButton;
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
        mDashboardList.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
        mAdapter = new DashboardCategoryListAdapter(getLayoutInflater(getArguments()));
        mDashboardList.setAdapter(mAdapter);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MezmurSearchActivity.class));
            }
        });
    }

    private void hideViews() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mFabButton.animate().translationY(mFabButton.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_layout;
    }

    @Override
    public void onLoadCategories(List<MezmurListItem> mezmurCategories) {
        mAdapter.addCategories(mezmurCategories);
    }
}
