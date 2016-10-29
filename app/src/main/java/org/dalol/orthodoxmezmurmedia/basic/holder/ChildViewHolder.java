package org.dalol.orthodoxmezmurmedia.basic.holder;

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