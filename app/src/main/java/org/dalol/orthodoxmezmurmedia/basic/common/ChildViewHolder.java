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
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.dalol.model.expandable.ExpandableMenu;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class ChildViewHolder extends RecyclerView.ViewHolder {

    protected ExpandableMenu mExpandableMenu;

    public ChildViewHolder(View itemView) {
        super(itemView);
    }

    @CallSuper
    public void bind(ExpandableMenu expandableMenu) {
        mExpandableMenu = expandableMenu;
    }
}