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

package org.dalol.orthodoxmezmurmedia.modules.pictures.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.holder.ItemViewHolder;
import org.dalol.model.callback.OnPictureOptionSelectListener;
import org.dalol.model.miscellaneous.ItemView;
import org.dalol.model.miscellaneous.ProgressInfo;
import org.dalol.model.pictures.PictureInfo;
import org.dalol.model.pictures.PictureModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class PicturesGridAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private OnPictureOptionSelectListener mOptionSelectListener;
    private List<ItemView> mPictureInfos = new ArrayList<>();

    public PicturesGridAdapter(OnPictureOptionSelectListener selectListener) {
        mOptionSelectListener = selectListener;
    }

//    public static class CardViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView mImageHolder;
//        TextView mTextTitle;
//        ImageView mImageOverflow;
//
//        public CardViewHolder(View itemView) {
//            super(itemView);
//            this.mImageHolder = (ImageView) itemView.findViewById(R.id.image_holder);
//            this.mTextTitle = (TextView) itemView.findViewById(R.id.text_title);
//            this.mImageOverflow = (ImageView) itemView.findViewById(R.id.image_overflow);
//        }
//    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (ItemView.ItemViewType.TYPE_PICTURE_ITEM.getViewType() == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_list_layout, parent, false);
        }
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return mPictureInfos.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (ItemView.ItemViewType.TYPE_PICTURE_ITEM.getViewType() == getItemViewType(position)) {
            bindPictureItem(holder, position);
        } else {
            bindProgressItem(holder, position);
        }
    }

    private void bindPictureItem(ItemViewHolder holder, int position) {
        holder.setHolderClickListener(new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                PictureInfo pictureInfo = (PictureInfo) mPictureInfos.get(position);
                mOptionSelectListener.onShowImage(pictureInfo, child.findViewById(R.id.image_holder));
            }
        });

        holder.setChildClickListener(R.id.image_overflow, new ItemViewHolder.ItemClickListener() {
            @Override
            public void onClick(View child, int position) {
                if (mOptionSelectListener != null) {
                    mOptionSelectListener.onShowOption(child, position);
                }
            }
        });

        PictureInfo pictureInfo = (PictureInfo) mPictureInfos.get(position);
        View itemView = holder.itemView;
        ImageView imageHolder = (ImageView) itemView.findViewById(R.id.image_holder);
        ImageView menuOverflow = (ImageView) itemView.findViewById(R.id.image_overflow);
        TextView textTitle = (TextView) itemView.findViewById(R.id.text_title);

        textTitle.setText(pictureInfo.getTitle());
        Glide.with(itemView.getContext())
                .load(pictureInfo.getImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageHolder);
    }

    private void bindProgressItem(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPictureInfos.size();
    }

    public void addPictures(@NonNull PictureModel pictureModel) {
        int previousSize = mPictureInfos.size();
        mPictureInfos.addAll(pictureModel.getPictureInfos());
        notifyItemRangeInserted(previousSize + 1, mPictureInfos.size());
    }

    public void removeProgressLayout() {
        int size = mPictureInfos.size();
        if (size > 0) {
            ItemView itemView = mPictureInfos.get(size-1);
            if (itemView.getViewType() == ItemView.ItemViewType.TYPE_PROGRESS_ITEM.getViewType()) {
                mPictureInfos.remove(itemView);
                notifyItemRemoved(mPictureInfos.size());
            }
        }
    }

    public void appendProgressLayout() {
        int size = mPictureInfos.size();
        mPictureInfos.add(new ProgressInfo());
        notifyItemInserted(mPictureInfos.size());
    }
}