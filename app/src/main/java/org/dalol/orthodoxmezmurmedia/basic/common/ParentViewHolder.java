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

package org.dalol.orthodoxmezmurmedia.basic.common;

import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.dalol.model.expandable.ExpandableMenu;
import org.dalol.model.expandable.ExpandedState;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public  abstract class ParentViewHolder extends RecyclerView.ViewHolder {

    private static final int ARROW_ROTATION_DURATION = 150;

    private final ImageView handle;
    private ExpandableMenu mExpandableMenu;
    private ExpandableItemActionListener mActionListener;

    public interface ExpandableItemActionListener {

        /**
         * This method will notify a click on a holder
         *
         * @param state
         * @param startPosition
         * @param totalCount
         */
        void onAction(ExpandedState state, int startPosition, int totalCount);
    }

    public ParentViewHolder(View itemView) {
        super(itemView);
        handle = (ImageView) itemView.findViewById(getArrowId());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                onParentHolderClick(v, position);
                List<ExpandableMenu> menuItems = mExpandableMenu.getMenuItems();
                int count = menuItems.size();
                if (isExpanded()) {
                    if (mActionListener != null) {
                        mActionListener.onAction(ExpandedState.COLLAPSED, position, count);
                    }
                    closeArrow();
                } else {
                    if (mActionListener != null) {
                        mActionListener.onAction(ExpandedState.EXPANDED, position, count);
                    }
                    openArrow();
                }
            }
        });
    }

    public void setActionListener(ExpandableItemActionListener actionListener) {
        mActionListener = actionListener;
    }

    @CallSuper
    public void bind(ExpandableMenu expandableMenu) {
        mExpandableMenu = expandableMenu;
        handle.setRotation(isExpanded() ? -90 : 90);
    }

    protected boolean isExpanded() {
        return mExpandableMenu.getState() == ExpandedState.EXPANDED;
    }

    public void openArrow() {
        if (handle != null) {
            handle.animate().setDuration(ARROW_ROTATION_DURATION).rotation(-90);
        }
    }

    public void closeArrow() {
        if (handle != null) {
            handle.animate().setDuration(ARROW_ROTATION_DURATION).rotation(90);
        }
    }

    @IdRes protected abstract int getArrowId();

    protected abstract void onParentHolderClick(View view, int position);
}
