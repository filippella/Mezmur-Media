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

package org.dalol.orthodoxmezmurmedia.modules.churches.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.holder.ItemViewHolder;
import org.dalol.model.churches.Church;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/14/2016
 */
public class ChurchListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private LayoutInflater mInflater;
    private List<Church> mChurchList = new ArrayList<>();

    public ChurchListAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item_church_location_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Church church = mChurchList.get(position);
        TextView prop1 = (TextView) holder.itemView.findViewById(R.id.textView1);
        TextView prop2 = (TextView) holder.itemView.findViewById(R.id.textView2);
        TextView prop3 = (TextView) holder.itemView.findViewById(R.id.textView3);
        TextView prop4 = (TextView) holder.itemView.findViewById(R.id.textView4);
        TextView prop5 = (TextView) holder.itemView.findViewById(R.id.textView5);
        TextView prop6 = (TextView) holder.itemView.findViewById(R.id.textView6);

        prop1.setText(church.getName());
        prop2.setText(church.getAddress());
        prop3.setText(church.getCountry());
        prop4.setText(church.getTabot());
        prop5.setText(church.getLatitude() + ", " + church.getLongitude());
        prop6.setText(church.getEstablishedin());
    }

    @Override
    public int getItemCount() {
        return mChurchList.size();
    }

    public void addChurch(Church church) {
        mChurchList.add(church);
        notifyItemInserted(mChurchList.size());
    }
}
