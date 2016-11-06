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

package org.dalol.presenter.business.feed;

import org.dalol.model.feed.RSSFeedItem;
import org.dalol.model.services.ApiType;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.mappers.feed.RSSFeedMapper;
import org.dalol.presenter.presentation.feed.RSSFeedView;
import org.dalol.presenter.services.feed.RSSFeedApi;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class RSSFeedPresenter extends BasePresenter<RSSFeedView, ResponseBody> {

    @Inject protected RSSFeedApi mRssFeedApi;
    @Inject protected RSSFeedMapper mRssMapper;

    @Inject
    public RSSFeedPresenter() {
    }

    @Override
    public void onViewReady() {
        super.onViewReady();
        getView().onShowDialog("Getting RSS feed");
        enqueue(ApiType.RSS_FEED, mRssFeedApi.getRssFeed("feed", "rss"));
    }

    @Override
    protected void onResponseRetrieved(boolean successful, Call<ResponseBody> call, Response<ResponseBody> response) {
        super.onResponseRetrieved(successful, call, response);

        RSSFeedView view = getView();
        view.onHideDialog();
        if(successful) {
            ResponseBody body = response.body();
            if (body != null) {
                try {
                    List<RSSFeedItem> feedItems = mRssMapper.mapResponse(body.byteStream());
                    if(!feedItems.isEmpty()) {
                        view.onLoadFeedList(feedItems);
                    } else {
                        view.onShowToast("Unable to load feed!!");
                    }
                } catch (XmlPullParserException e) {
                    view.onShowToast(e.getMessage());
                } catch (IOException e) {
                    view.onShowToast(e.getMessage());
                }
            }
        } else {
            view.onShowToast("Failed to load RSS feed!!!");
        }
    }
}
