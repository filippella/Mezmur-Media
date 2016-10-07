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

package org.dalol.orthodoxmezmurmedia.mvp.model.pictures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public class PictureModel {

    private List<PictureInfo> pictureInfos = new ArrayList<>();
    private String previousLink;
    private String nextLink;

    public void setPreviousLink(String previousLink) {
        this.previousLink = previousLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public void addPicture(PictureInfo pictureInfo) {
        pictureInfos.add(pictureInfo);
    }

    public void setPictureInfos(List<PictureInfo> pictureInfos) {
        this.pictureInfos = pictureInfos;
    }

    public List<PictureInfo> getPictureInfos() {
        return pictureInfos;
    }

    public String getPreviousLink() {
        return previousLink;
    }

    public String getNextLink() {
        return nextLink;
    }
}
