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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.model.expandable.ExpandableMenu;
import org.dalol.model.expandable.ExpandableType;
import org.dalol.model.expandable.ExpandedState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public class OtherMenuAdapter extends RecyclerView.Adapter<OtherMenuAdapter.MenuHolder> {

    private final List<ExpandableMenu> mAllList = new ArrayList<>();
    private static final int ARROW_ROTATION_DURATION = 150;
    private boolean mAccordionMode;

    public OtherMenuAdapter() {
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ExpandableType.TYPE_MENU.getType()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_church_location_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_list_layout, parent, false);
        }
        return new MenuHolder(viewType, view);
    }

    @Override
    public int getItemViewType(int position) {
        return mAllList.get(position).getExpandableType().getType();
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.bind(getItemViewType(position));
//        if (getItemViewType(position) == ExpandableType.TYPE_MENU.getType()) {
//            bindExpandableMenuHolder((ExpandableMenuHolder) holder, position);
//        } else {
//            bindExpandableItem((ExpandableItemHolder) holder, position);
//        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void collapseAllExcept(int position) {
        for (int i = mAllList.size() - 1; i >= 0; i--) {
            if (i != position && getItemViewType(i) == ExpandableType.TYPE_MENU.getType()) {
                if (isExpanded(i)) {
                    collapseSubMenus(i, null, true);
                }
            }
        }
    }

    public void collapseAll() {
        collapseAllExcept(-1);
    }

    public void expandAll() {
        for (int i=mAllList.size()-1; i>=0; i--) {
            if (getItemViewType(i) == ExpandableType.TYPE_MENU.getType()) {
                if (!isExpanded(i)) {
                    expandSubMenus(i, null, true);
                }
            }
        }
    }

    private void expandSubMenus(int position, ImageView handle, boolean b) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        List<ExpandableMenu> menuItems = expandableMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            mAllList.add(position + i + 1, menuItems.get(i));
            notifyItemInserted(position + 1);
        }
        expandableMenu.setExpandedState(ExpandedState.EXPANDED);
        if (handle != null) {
            openArrow(handle);
        }

        if (b) {
            notifyItemChanged(position);
        }
        if (mAccordionMode) {
            collapseAllExcept(position);
        }
    }

    private void collapseSubMenus(int position, ImageView handle, boolean b) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        List<ExpandableMenu> menuItems = expandableMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            mAllList.remove(menuItems.get(i));
//            mSubMenuList.add(menuItems)
            //addMenu(menuItems.get(i));
        }
        //notifyDataSetChanged();
        expandableMenu.setExpandedState(ExpandedState.COLLAPSED);
        if (handle != null) {
            closeArrow(handle);
        }
        notifyItemRangeRemoved(position + 1, menuItems.size());
        if (b) {
            notifyItemChanged(position);
        }
    }

    public void openArrow(View view) {
        view.animate().setDuration(ARROW_ROTATION_DURATION).rotation(-90);
    }

    public void closeArrow(View view) {
        view.animate().setDuration(ARROW_ROTATION_DURATION).rotation(0);
    }

    public void addMenu(ExpandableMenu expandableMenu) {
        mAllList.add(expandableMenu);
    }

    @Override
    public int getItemCount() {
        return mAllList.size();
    }

    public void setAccordionMode() {
        mAccordionMode = true;
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        private ImageView handle;

        public MenuHolder(int viewType, View itemView) {
            super(itemView);
            if (viewType == ExpandableType.TYPE_MENU.getType()) {
                handle = (ImageView) itemView.findViewById(R.id.imageArrow);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getLayoutPosition();

                        if(isExpanded(position)) {
                            collapseSubMenus(position, handle, false);
                        } else {
                            expandSubMenus(position, handle, false);

                        }
                    }
                });
            }

        }

        public void bind(int viewType) {
            if (viewType == ExpandableType.TYPE_ITEM.getType()) {
                ExpandableMenu<String> expandedItem = mAllList.get(getLayoutPosition());
                TextView title = (TextView) itemView.findViewById(R.id.textView);
                title.setText(expandedItem.getMenuInfo());
            } else {
                //Do nothing
                handle.setRotation(isExpanded(getLayoutPosition()) ? 90 : 0);
            }
        }
    }

    private boolean isExpanded(int position) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        return expandableMenu.getState() == ExpandedState.EXPANDED;
    }

//    public class ItemHolder extends RecyclerView.ViewHolder {
//
//        public ItemHolder(View itemView) {
//            super(itemView);
//        }
//
//        public void bind() {
//            ExpandableMenu<String> expandedItem = mAllList.get(getLayoutPosition());
//            TextView title = (TextView) itemView.findViewById(R.id.textView);
//            title.setText(expandedItem.getMenuInfo());
//        }
//    }
}
