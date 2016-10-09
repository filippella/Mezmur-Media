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

package org.dalol.mezmurmedia.modules.mezmur.adapter;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.modules.details.MezmurDetailActivity;
import org.dalol.mezmurmedia.utilities.helpers.ColorGenerator;
import org.dalol.mezmurmedia.utilities.widgets.RecyclerViewFastIndexer;
import org.dalol.model.mezmur.Mezmur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filippo.ash on 7/29/16.
 */
public class MezmurListAdapter extends RecyclerView.Adapter<MezmurListAdapter.Holder> implements RecyclerViewFastIndexer.BubbleTextGetter {

    //    private final List<Integer> values;
//    private HashMap<String, Integer> mapIndex = new LinkedHashMap<>();
    private LayoutInflater inflater;
    private List<String> items;
    private char[] dataSet;
    private TextView view;
    private List<Mezmur> mezmurList = new ArrayList<>();
    private ColorGenerator colorGenerator;
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //    public MezmurListAdapter(int numberOfItems) {
//        List<String> items = new ArrayList<>();
//        java.util.Random r = new java.util.Random();
//        for (int i = 0; i < numberOfItems; i++)
//            items.add(((char) ('A' + r.nextInt('Z' - 'A'))) + " " + Integer.toString(i));
//        java.util.Collections.sort(items);
//        this.items = items;
//    }

//    public MezmurListAdapter(LayoutInflater inflater, List<Item> items) {
//        this.inflater = inflater;
//        this.items = items;
//
//        String current = "";
//
//        for (int x = 0; x < items.size(); x++) {
//            Item item = items.get(x);
//            String ch = item.getName().substring(0, 1);
//            ch = ch.toLowerCase();
//            if (!current.equals(ch)) {
//                current = ch;
//                item.setHeaderType(true);
//            }
//        }
////
////        Log.d(MezmurListAdapter.class.getSimpleName(), mapIndex.toString());
////        Log.d(MezmurListAdapter.class.getSimpleName(), mapIndex.keySet().toString());
////        values = mapIndex.values();
//        //Log.d(MezmurListAdapter.class.getSimpleName(), values.toString());
//    }

    public MezmurListAdapter(LayoutInflater layoutInflater) {
        this.inflater = layoutInflater;
        colorGenerator = ColorGenerator.MATERIAL;
        //List<String> items = new ArrayList<>();
//        java.util.Random r = new java.util.Random();
//        for (int i = 0; i < items.size(); i++)
//            items.add(((char) ('A' + r.nextInt('Z' - 'A'))) + " " + Integer.toString(i));
//        java.util.Collections.sort(items);
        //this.items = items;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(this.inflater.inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Mezmur mezmur = mezmurList.get(position);

        //String item = this.items.get(position);

        String mezmurTitle = mezmur.getTitle().trim();
        holder.itemTitle.setText(mezmurTitle);
        holder.itemTitleCounter.setText("Mezmur " + position);

        if (isHeader(position)) {
            GradientDrawable drawable = (GradientDrawable) holder.indicator.getBackground();
            String text = mezmurTitle.substring(0, 1).toUpperCase();
            drawable.setColor(colorGenerator.getColor(text));
                holder.indicator.setText(text);


            holder.indicator.setVisibility(TextView.VISIBLE);
        } else {
            holder.indicator.setVisibility(TextView.INVISIBLE);
        }

        holder.itemMoreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
            }
        });

//        if (isHeaderType(position)) {
//            holder.indicator.setText(item.substring(0, 1));
//        } else {
//            holder.indicator.setText("");
//        }

//        values.
//
//        if(mapIndex.containsValue(position)) {
//            holder.indicator.setText(getKeyFromValue(mapIndex.get(position)));
//        }

        //String indicatorCharFor = getIndicatorCharFor(position);

    }
//
//    public String getKeyFromValue(Integer value) {
//        for (String o : mapIndex.keySet()) {
//            if (mapIndex.get(o).equals(value)) {
//                return o;
//            }
//        }
//        return null;
//    }

//    public boolean isHeaderType(int position) {
//        Item item = this.items.get(position);
//        return item.isHeaderType(position);
//    }

    @Override
    public int getItemCount() {
        return this.mezmurList.size();
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        return Character.toString(mezmurList.get(pos).getTitle().charAt(0));
    }

    public boolean shouldStartTranslate(int pos) {
        if (mezmurList.size() < pos) return false;
        if (pos == 0) return true;
        return !isSameChar(mezmurList.get(pos - 1).getTitle().charAt(0), mezmurList.get(pos).getTitle().charAt(0));
    }


    public void setView(TextView view) {
        this.view = view;
    }

    public TextView getView() {
        return view;
    }

    public void appen(Mezmur mezmur) {
        mezmurList.add(mezmur);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView itemMoreOption;
        private TextView indicator;
        private TextView itemTitle;
        private TextView itemTitleCounter;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            indicator = (TextView) itemView.findViewById(R.id.indicatorCharacter);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemTitleCounter = (TextView) itemView.findViewById(R.id.itemTitleCounter);
            itemMoreOption = (ImageView) itemView.findViewById(R.id.mezmur_list_item_more_option);
        }

        @Override
        public void onClick(View v) {
            String text = mezmurList.get(getAdapterPosition()).getText();
            String title = mezmurList.get(getAdapterPosition()).getTitle();
            Intent intent = new Intent(v.getContext(), MezmurDetailActivity.class);

            String letter = text.substring(0, 1).toUpperCase();

//            Intent intent = new Intent(v.getContext(), ZoomableMezmurDetailActivity.class);
            intent.putExtra(MezmurDetailActivity.MEZMUR_TITLE, title);
            intent.putExtra(MezmurDetailActivity.MEZMUR_DETAIL, text);
            //intent.putExtra(MezmurDetailActivity.MEZMUR_COLOR_CODE, colorGenerator.getColor(letter));
            v.getContext().startActivity(intent);
            //Toast.makeText(v.getContext(), text, Toast.LENGTH_LONG).show();
        }

    }

    private Boolean isHeader(int pos) {
        if (pos == 0) {
            return Boolean.TRUE;
        } else if (mezmurList.get(pos - 1).getTitle().substring(0, 1).toLowerCase()
                .equalsIgnoreCase(mezmurList.get(pos).getTitle().substring(0, 1).toLowerCase())) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    private Boolean isSameChar(char prev, char curr) {
        if (Character.toLowerCase(prev) == Character.toLowerCase(curr)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public interface ItemClickListener {

        void onClick(View view);
    }
}
