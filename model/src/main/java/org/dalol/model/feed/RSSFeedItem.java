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

package org.dalol.model.feed;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/6/2016
 */
public class RSSFeedItem {

    private String publishedDate;
    private String category;
    private String author;
    private String description;
    private String guid;
    private String link;
    private String title;

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getGuid() {
        return guid;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}
