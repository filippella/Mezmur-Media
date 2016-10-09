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

package org.dalol.mezmurmedia.utilities.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class RecyclerGridItemMarginDecorator extends RecyclerView.ItemDecoration {

    private final int spanCount;
    private int spacing;
    private int halfSpace;
    private int lastItemInFirstLane;

    public RecyclerGridItemMarginDecorator(int spacing) {
        this.spacing = spacing;
        halfSpace = spacing /2;
        this.spanCount = 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int columnCount = 2;
        int position = parent.getChildAdapterPosition(view);

        outRect.right = spacing;
        outRect.bottom = spacing;

        if (position < columnCount) {
            outRect.top = spacing;
        }

        if (position % columnCount == 0) {
            outRect.left = spacing;
        }
    }
}