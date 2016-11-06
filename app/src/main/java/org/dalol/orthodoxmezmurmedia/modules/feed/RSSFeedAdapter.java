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

package org.dalol.orthodoxmezmurmedia.modules.feed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dalol.model.feed.RSSFeedItem;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.common.BaseRecyclerAdapter;
import org.dalol.orthodoxmezmurmedia.basic.common.BaseViewHolder;
import org.dalol.orthodoxmezmurmedia.modules.zoomable.ZoomableMezmurDetailActivity;
import org.dalol.orthodoxmezmurmedia.utilities.custom.AmharicTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class RSSFeedAdapter extends BaseRecyclerAdapter<RSSFeedAdapter.RSSFeedHolder, RSSFeedItem> {

    public RSSFeedAdapter(Context context) {
        super(context);
    }

    @Override
    protected RSSFeedHolder onCreateHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RSSFeedHolder(inflater.inflate(R.layout.item_rss_feed_layout, parent, false));
    }

    @Override
    protected void bindHolder(RSSFeedHolder holder, int position) {
        RSSFeedItem feedItem = mContentList.get(position);
        holder.mRssTitle.setText(feedItem.getTitle());
        holder.mRssPublishedDate.setText(feedItem.getPublishedDate());
    }

    public class RSSFeedHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.rss_feed_title) protected AmharicTextView mRssTitle;
        @BindView(R.id.rss_feed_published_date) protected TextView mRssPublishedDate;

        public RSSFeedHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RSSFeedItem feedItem = mContentList.get(getAdapterPosition());
            Context context = v.getContext();
            Intent intent = new Intent(context, ZoomableMezmurDetailActivity.class);
            intent.putExtra(ZoomableMezmurDetailActivity.KEY_CONTENT_TO_LOAD, feedItem.getDescription());
            intent.putExtra(ZoomableMezmurDetailActivity.KEY_CONTENT_TITLE, feedItem.getTitle());
            context.startActivity(intent);
        }
    }
}
