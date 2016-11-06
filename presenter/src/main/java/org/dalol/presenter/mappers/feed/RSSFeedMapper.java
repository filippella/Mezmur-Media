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

package org.dalol.presenter.mappers.feed;

import org.dalol.model.feed.RSSFeedItem;
import org.dalol.model.feed.XmlElements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class RSSFeedMapper {

    @Inject
    public RSSFeedMapper() {
    }

    public List<RSSFeedItem> mapResponse(InputStream inputStream) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(inputStream, null);

        int eventType = xpp.getEventType();
        RSSFeedItem feedItem = null;
        String text = null;

        List<RSSFeedItem> feedItems = new ArrayList<>();

//        while (eventType != XmlPullParser.END_DOCUMENT) {
//            if(eventType == XmlPullParser.START_DOCUMENT) {
//                System.out.println("Start document");
//            } else if(eventType == XmlPullParser.END_DOCUMENT) {
//                System.out.println("End document");
//            } else if(eventType == XmlPullParser.START_TAG) {
//                System.out.println("Start tag "+xpp.getName());
//            } else if(eventType == XmlPullParser.END_TAG) {
//                System.out.println("End tag "+xpp.getName());
//            } else if(eventType == XmlPullParser.TEXT) {
//                System.out.println("Text "+xpp.getText());
//            }
//            eventType = xpp.next();
//        }

        //int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = xpp.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (XmlElements.ITEM.equals(tagName)) {
                        feedItem = new RSSFeedItem();
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = xpp.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (XmlElements.ITEM.equals(tagName) && feedItem != null) {
                        feedItems.add(feedItem);
                    } else if (XmlElements.TITLE.equals(tagName) && feedItem != null) {
                        feedItem.setTitle(text);
                    } else if (XmlElements.LINK.equals(tagName) && feedItem != null) {
                        feedItem.setLink(text);
                    } else if (XmlElements.GUID.equals(tagName) && feedItem != null) {
                        feedItem.setGuid(text);
                    } else if (XmlElements.DESCRIPTION.equals(tagName) && feedItem != null) {
                        feedItem.setDescription(text);
                    } else if (XmlElements.AUTHOR.equals(tagName) && feedItem != null) {
                        feedItem.setAuthor(text);
                    } else if (XmlElements.CATEGORY.equals(tagName) && feedItem != null) {
                        feedItem.setCategory(text);
                    } else if (XmlElements.PUBLISH_DATE.equals(tagName) && feedItem != null) {
                        feedItem.setPublishedDate(text);
                    }
                    break;
                default:
                    break;
            }
            eventType = xpp.next();
        }

//
//        while (eventType != XmlPullParser.END_DOCUMENT) {
//            if (eventType == XmlPullParser.START_DOCUMENT) {
//            } else if (eventType == XmlPullParser.END_DOCUMENT) {
//            } else if (eventType == XmlPullParser.START_TAG) {
//                String tagName = xpp.getName();
//                if (XmlElements.ITEM.equals(tagName)) {
//                    feedItem = new RSSFeedItem();
//                }
//            } else if (eventType == XmlPullParser.TEXT) {
//                text = xpp.getText();
//            } else if (eventType == XmlPullParser.END_TAG) {
//                String endTagName = xpp.getName();
//                if (XmlElements.ITEM.equals(endTagName)) {
//                    feedItems.add(feedItem);
//                } else if (XmlElements.TITLE.equals(endTagName)) {
//                    feedItem.setTitle(text);
//                } else if (XmlElements.LINK.equals(endTagName)) {
//                    feedItem.setLink(text);
//                } else if (XmlElements.GUID.equals(endTagName)) {
//                    feedItem.setGuid(text);
//                } else if (XmlElements.DESCRIPTION.equals(endTagName)) {
//                    feedItem.setDescription(text);
//                } else if (XmlElements.AUTHOR.equals(endTagName)) {
//                    feedItem.setAuthor(text);
//                } else if (XmlElements.CATEGORY.equals(endTagName)) {
//                    feedItem.setCategory(text);
//                } else if (XmlElements.PUBLISH_DATE.equals(endTagName)) {
//                    feedItem.setPublishedDate(text);
//                }
//            }
//            eventType = xpp.next();
//        }
        return feedItems;
    }
}
