package org.dalol.orthodoxmezmurmedia.basic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.dalol.model.mezmur.MezmurListItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.holder.ItemViewHolder;
import org.dalol.orthodoxmezmurmedia.modules.zoomable.ZoomableMezmurDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/30/2016
 */
public class MezmurSearchListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<MezmurListItem> mCategories = new ArrayList<>();

    public MezmurSearchListAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_mezmur_search_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setHolderClickListener(new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                Context context = child.getContext();
                Toast.makeText(context, "Clicked on position -> " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ZoomableMezmurDetailActivity.class);
                intent.putExtra(ZoomableMezmurDetailActivity.MEZMUR_DETAIL, "Zoomable mezmur content");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 350;
    }
}