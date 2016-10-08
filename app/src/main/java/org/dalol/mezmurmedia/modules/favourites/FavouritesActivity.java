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

package org.dalol.mezmurmedia.modules.favourites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.business.base.BaseActivity;
import org.dalol.mezmurmedia.mvp.model.adapter.FavouritesAdapter;
import org.dalol.mezmurmedia.utilities.helpers.RecyclerListItemMarginDecorator;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/1/2016
 */
public class FavouritesActivity extends BaseActivity {

    @BindView(R.id.recycler_view_FavoriteList) protected RecyclerView mFavoriteList;

    public static void start(Activity activity, Bundle bundle) {
        activity.startActivity(new Intent(activity, FavouritesActivity.class).putExtras(bundle));
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, FavouritesActivity.class));
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        showHome();
        setTitle("Favourites");
        mFavoriteList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mFavoriteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mFavoriteList.addItemDecoration(new RecyclerListItemMarginDecorator(getResources().getDimensionPixelSize(R.dimen.recent_mezmur_item_margin_size)));
        mFavoriteList.setAdapter(new FavouritesAdapter());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_favourites;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
