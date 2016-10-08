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

package org.dalol.mezmurmedia.mvp.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.modules.mezmur.MezmurListsActivity;
import org.dalol.mezmurmedia.utilities.helpers.ItemViewHolder;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/1/2016
 */
public class DashboardCategoryListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private LayoutInflater mLayoutInflater;

    public DashboardCategoryListAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_dashboard_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setHolderClickListener(new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                Context context = child.getContext();
                context.startActivity(new Intent(context, MezmurListsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}