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

import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.mvp.model.adapter.BaseMezmurAdapter;
import org.dalol.orthodoxmezmurmedia.modules.mezmur.MezmurListsActivity;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.ItemViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/1/2016
 */
public class MezmurCategoryListBinder implements ViewAdapterBinder<String, BaseMezmurAdapter> {

    @Inject
    public MezmurCategoryListBinder() {
    }

    @Override
    public int getViewHolderLayout(int viewType) {
        return R.layout.item_dashboard_list_layout;
    }

    @Override
    public void bindView(ItemViewHolder viewHolder, int position) {
        viewHolder.setHolderClickListener(new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                Context context = child.getContext();
                context.startActivity(new Intent(context, MezmurListsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void addItem(String item) {

    }

    @Override
    public void addItem(String item, int position) {

    }

    @Override
    public void setItems(List<String> items) {

    }

    @Override
    public void addItems(List<String> items) {

    }

    @Override
    public void setAdapter(BaseMezmurAdapter adapter) {

    }
}
