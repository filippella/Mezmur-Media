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

package org.dalol.mezmurmedia.utilities.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener mListener;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setHolderClickListener(ItemClickListener listener) {
        mListener = listener;
    }

    public void setChildClickListener(int viewId, final ItemClickListener listener) {
        View child = itemView.findViewById(viewId);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v, getAdapterPosition());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {

        void onClick(View child, int position);
    }
}
