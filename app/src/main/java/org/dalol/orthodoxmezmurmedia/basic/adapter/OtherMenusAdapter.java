package org.dalol.orthodoxmezmurmedia.basic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.dalol.model.expandable.ExpandableMenu;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.binders.OtherMenusInfo;
import org.dalol.orthodoxmezmurmedia.basic.holder.ChildViewHolder;
import org.dalol.orthodoxmezmurmedia.basic.holder.ParentViewHolder;
import org.dalol.orthodoxmezmurmedia.modules.holybooks.HolyBooksActivity;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class OtherMenusAdapter extends BaseExpandableRecyclerAdapter<OtherMenusAdapter.MyParentViewHolder, OtherMenusAdapter.MyChildViewHolder> {

    public OtherMenusAdapter(Context context) {
        super(context);
    }

    @Override
    protected MyChildViewHolder onCreateChildViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MyChildViewHolder(inflater.inflate(R.layout.item_child_layout, parent, false));
    }

    @Override
    protected MyParentViewHolder onCreateParentViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new MyParentViewHolder(inflater.inflate(R.layout.item_parent_layout, parent, false));
    }

    @Override
    protected void onBindChildViewHolder(MyChildViewHolder holder) {
    }

    @Override
    protected void onBindParentViewHolder(MyParentViewHolder holder) {

    }

    public class MyParentViewHolder extends ParentViewHolder {

        private final TextView mMenuTitle;

        public MyParentViewHolder(View itemView) {
            super(itemView);
            mMenuTitle = (TextView) itemView.findViewById(R.id.textView);
        }

        @Override
        public void bind(ExpandableMenu expandableMenu) {
            super.bind(expandableMenu);
            mMenuTitle.setText(expandableMenu.getMenuInfo().toString());
        }

        @Override
        protected void onParentHolderClick(View view, int position) {

        }

        @Override
        protected int getArrowId() {
            return R.id.handle;
        }
    }

    public class MyChildViewHolder extends ChildViewHolder implements View.OnClickListener {

        private final TextView mMenuTitle;

        public MyChildViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMenuTitle = (TextView) itemView.findViewById(R.id.textView);
        }

        @Override
        public void bind(ExpandableMenu expandableMenu) {
            super.bind(expandableMenu);
            OtherMenusInfo id = OtherMenusInfo.getById((Integer) expandableMenu.getMenuInfo());
            mMenuTitle.setText(id.getTitle());
        }

        @Override
        public void onClick(View v) {
            Integer info = (Integer) mExpandableMenu.getMenuInfo();
            OtherMenusInfo id = OtherMenusInfo.getById(info);
            Context context = v.getContext();
            Intent intent = new Intent(context, HolyBooksActivity.class);
            intent.putExtra(HolyBooksActivity.MENU_ID, id.getId());
            intent.putExtra(HolyBooksActivity.MENU_DATA_SOURCE, id.getDataSource());
            context.startActivity(intent);
            Toast.makeText(context, info + " -- Child clicked on Position " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}