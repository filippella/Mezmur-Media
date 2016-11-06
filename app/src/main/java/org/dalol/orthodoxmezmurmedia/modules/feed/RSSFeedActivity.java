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

package org.dalol.orthodoxmezmurmedia.modules.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.dalol.model.feed.RSSFeedItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerRSSFeedComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ApiModule;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.RSSFeedModule;
import org.dalol.orthodoxmezmurmedia.utilities.custom.RecyclerListItemMarginDecorator;
import org.dalol.presenter.business.feed.RSSFeedPresenter;
import org.dalol.presenter.presentation.feed.RSSFeedView;

import java.util.List;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class RSSFeedActivity extends BaseActivity<RSSFeedPresenter> implements RSSFeedView {

    @BindView(R.id.swipe_to_refresh_rss_feed) protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rss_feed_list) protected RecyclerView mFeedList;
    private RSSFeedAdapter mFeedAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("EOTCSSD.ORG Feeds");
        mFeedList.setHasFixedSize(true);
        mFeedList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mFeedList.addItemDecoration(new RecyclerListItemMarginDecorator(getResources().getDimensionPixelSize(R.dimen.recent_mezmur_item_margin_size)));
        mFeedAdapter = new RSSFeedAdapter(this);
        mFeedList.setAdapter(mFeedAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.accent),
                ContextCompat.getColor(this, R.color.nice_green),
                ContextCompat.getColor(this, R.color.orange));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().onViewReady();
            }
        });
        getPresenter().onViewReady();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_rss_feed;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    protected void resolveDependency() {
        super.resolveDependency();
        DaggerRSSFeedComponent.builder()
                .apiModule(new ApiModule("http://eotcssd.org"))
                .applicationComponent(getApplicationComponent())
                .rSSFeedModule(new RSSFeedModule(this))
                .build().inject(this);
    }

    @Override
    public void onShowToast(String message) {
        super.onShowToast(message);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadFeedList(List<RSSFeedItem> feedItems) {
        mFeedAdapter.clear();
        mFeedAdapter.addList(feedItems);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
