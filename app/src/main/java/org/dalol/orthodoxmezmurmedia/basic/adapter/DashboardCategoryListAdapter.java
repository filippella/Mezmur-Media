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

package org.dalol.orthodoxmezmurmedia.basic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.binders.MezmurCategoryInfo;
import org.dalol.orthodoxmezmurmedia.basic.holder.ItemViewHolder;
import org.dalol.orthodoxmezmurmedia.modules.mezmur.MezmurListsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/1/2016
 */
public class DashboardCategoryListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<MezmurListItem> mCategories = new ArrayList<>();
    private int[] images = {R.drawable.mariam, R.drawable.saint_gebriel, R.drawable.giorgis, R.drawable.silasie,
            R.drawable.yared};

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
                MezmurListItem mezmurListItem = mCategories.get(position);
                Context context = child.getContext();

                Intent intent = new Intent(context, MezmurListsActivity.class);
                intent.putExtra(MezmurListsActivity.ITEM_BUNDLE_INFO, mezmurListItem);
                context.startActivity(intent);

//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    Activity activity = (Activity) context;
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, child.findViewById(R.id.mezmur_category_icon), "image");
//                    context.startActivity(intent, options.toBundle());
//                } else {
//                    context.startActivity(intent);
//                }
            }
        });
        View itemView = holder.itemView;
        Context context = itemView.getContext();
        ImageView categoryImage = (ImageView) itemView.findViewById(R.id.mezmur_category_icon);
        TextView categoryTitle = (TextView) itemView.findViewById(R.id.mezmur_category_title);
        TextView categoryCount = (TextView) itemView.findViewById(R.id.mezmur_category_count);
        MezmurListItem mezmurListItem = mCategories.get(position);
        categoryTitle.setText(mezmurListItem.getName());
        categoryCount.setText(String.format("%d መዝሙራት", mezmurListItem.getMezmurIdList().size()));
        MezmurCategoryInfo info = MezmurCategoryInfo.getByCategoryId(Integer.parseInt(mezmurListItem.getCid()));
        Glide.with(context).load(info.getResId()).into(categoryImage);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void addCategories(List<MezmurListItem> categories) {
        int initialSize = mCategories.size();
        mCategories.addAll(categories);
        notifyItemRangeInserted(initialSize, mCategories.size());
    }

    public int getImage() {
        return images[new Random().nextInt(images.length)];
    }
}