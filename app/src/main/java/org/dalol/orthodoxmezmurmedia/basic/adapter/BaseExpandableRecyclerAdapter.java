package org.dalol.orthodoxmezmurmedia.basic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.dalol.model.expandable.ExpandableMenu;
import org.dalol.model.expandable.ExpandableType;
import org.dalol.model.expandable.ExpandedState;
import org.dalol.orthodoxmezmurmedia.basic.holder.ChildViewHolder;
import org.dalol.orthodoxmezmurmedia.basic.holder.ParentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public abstract class BaseExpandableRecyclerAdapter<PVH extends ParentViewHolder, CVH extends ChildViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ParentViewHolder.ExpandableItemActionListener {

    protected final List<ExpandableMenu> mAllList = new ArrayList<>();
    private LayoutInflater mInflater;
    private boolean mAccordionStyle;

    public BaseExpandableRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExpandableType expandableType = ExpandableType.fromValue(viewType);
        switch (expandableType) {
            case TYPE_PARENT:
                PVH pvh = onCreateParentViewHolder(mInflater, parent);
                pvh.setActionListener(BaseExpandableRecyclerAdapter.this);
                return pvh;
            default:
                CVH cvh = onCreateChildViewHolder(mInflater, parent);
                return cvh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        ExpandableType expandableType = ExpandableType.fromValue(getItemViewType(position));
        switch (expandableType) {
            case TYPE_PARENT:
                PVH pvh = (PVH) holder;
                pvh.bind(expandableMenu);
                onBindParentViewHolder(pvh);
                break;
            case TYPE_CHILD:
                CVH cvh = (CVH) holder;
                cvh.bind(expandableMenu);
                onBindChildViewHolder(cvh);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mAllList.get(position).getExpandableType().getType();
    }

    @Override
    public int getItemCount() {
        return mAllList.size();
    }

    public void setAccordionStyle(boolean style) {
        mAccordionStyle = style;
        notifyDataSetChanged();
    }

    public void addMenu(ExpandableMenu expandableMenu) {
        mAllList.add(expandableMenu);
    }

    public void setExpandableMenuList(List<ExpandableMenu> expandableMenuList) {
        mAllList.addAll(expandableMenuList);
    }

    private void expandSubMenus(int position, boolean notifyChange) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        List<ExpandableMenu> menuItems = expandableMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            mAllList.add(position + i + 1, menuItems.get(i));
            notifyItemInserted(position + 1);
        }
        expandableMenu.setExpandedState(ExpandedState.EXPANDED);

        if (notifyChange) {
            notifyItemChanged(position);
        }
        if(mAccordionStyle) {
            collapseAllExcept(position);
        }
    }

    private void collapseSubMenus(int position, boolean notifyChange) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        List<ExpandableMenu> menuItems = expandableMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            mAllList.remove(menuItems.get(i));
        }
        expandableMenu.setExpandedState(ExpandedState.COLLAPSED);
        notifyItemRangeRemoved(position + 1, menuItems.size());
        if (notifyChange) {
            notifyItemChanged(position);
        }
    }

    public void collapseAllExcept(int position) {
        for (int i = mAllList.size() - 1; i >= 0; i--) {
            if (i != position && getItemViewType(i) == ExpandableType.TYPE_PARENT.getType()) {
                if (isExpanded(i)) {
                    collapseSubMenus(i, true);
                }
            }
        }
    }

    private boolean isExpanded(int position) {
        ExpandableMenu expandableMenu = mAllList.get(position);
        return expandableMenu.getState() == ExpandedState.EXPANDED;
    }

    @Override
    public void onAction(ExpandedState state, int startPosition, int totalCount) {
        switch (state) {
            case EXPANDED:
                expandSubMenus(startPosition, false);
                break;
            case COLLAPSED:
                collapseSubMenus(startPosition, false);
                break;
        }
    }

    protected abstract CVH onCreateChildViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected abstract PVH onCreateParentViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected abstract void onBindChildViewHolder(CVH holder);

    protected abstract void onBindParentViewHolder(PVH holder);
}