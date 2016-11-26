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

package org.dalol.orthodoxmezmurmedia.basic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/26/2016
 */
public abstract class BaseRecyclerViewAdapter<H extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<I> mCollections = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        H holder = onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindHolder((H) holder, position);
    }

    @Override
    public int getItemCount() {
        return mCollections.size();
    }

    public void addItem(I item) {
        int capacity = mCollections.size();
        mCollections.add(item);
        notifyItemInserted(capacity);
    }

    public List<I> getCollections() {
        return mCollections;
    }

    protected abstract H onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    protected abstract void bindHolder(H holder, int position);
}