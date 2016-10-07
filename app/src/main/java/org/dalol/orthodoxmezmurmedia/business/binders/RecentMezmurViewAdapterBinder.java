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

package org.dalol.orthodoxmezmurmedia.business.binders;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.mvp.model.adapter.BaseMezmurAdapter;
import org.dalol.orthodoxmezmurmedia.mvp.model.mezmur.RecentMezmur;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class RecentMezmurViewAdapterBinder implements ViewAdapterBinder<RecentMezmur, BaseMezmurAdapter> {

    private List<RecentMezmur> mRecentMezmurList = new ArrayList<>();
    private BaseMezmurAdapter mAdapter;

    @Inject
    public RecentMezmurViewAdapterBinder() {
    }

    @Override
    public int getViewHolderLayout(int viewType) {
        return R.layout.item_recent_list_layout;
    }

    @Override
    public void bindView(ItemViewHolder viewHolder, int position) {
        viewHolder.setHolderClickListener(new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                mAdapter.notifyItemRemoved(position);
                Snackbar.make(child, "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "Undo", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void addItem(RecentMezmur item) {
        mRecentMezmurList.add(item);
    }

    @Override
    public void addItem(RecentMezmur item, int position) {
        mRecentMezmurList.add(position, item);
    }

    @Override
    public void setItems(List<RecentMezmur> items) {
        mRecentMezmurList.clear();
        mRecentMezmurList.addAll(items);
    }

    @Override
    public void addItems(List<RecentMezmur> items) {
        mRecentMezmurList.addAll(items);
    }

    @Override
    public void setAdapter(BaseMezmurAdapter adapter) {
        mAdapter = adapter;
    }
}
