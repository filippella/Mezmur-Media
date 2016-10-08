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

package org.dalol.mezmurmedia.mvp.model.pictures;

public class PictureResponse {

    private String product;
    private String releaseDate;
    private String previousLink;
    private Cakes[] cakes;
    private String nextLink;
    private String version;

    public String getProduct() {
        return product;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPreviousLink() {
        return previousLink;
    }

    public Cakes[] getCakes() {
        return cakes;
    }

    public String getNextLink() {
        return nextLink;
    }

    public String getVersion() {
        return version;
    }
}
