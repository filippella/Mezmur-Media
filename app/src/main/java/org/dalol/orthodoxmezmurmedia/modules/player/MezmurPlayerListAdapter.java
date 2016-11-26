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

package org.dalol.orthodoxmezmurmedia.modules.player;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.BaseRecyclerViewAdapter;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/26/2016
 */
public class MezmurPlayerListAdapter extends BaseRecyclerViewAdapter<MezmurPlayerListAdapter.MezmurPlayerItemViewHolder, String> {

    @Override
    protected MezmurPlayerItemViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new MezmurPlayerItemViewHolder(inflater.inflate(R.layout.item_mezmur_player_layout, parent, false));
    }

    @Override
    protected void bindHolder(MezmurPlayerItemViewHolder holder) {

    }

    public class MezmurPlayerItemViewHolder extends RecyclerView.ViewHolder {

        public MezmurPlayerItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
