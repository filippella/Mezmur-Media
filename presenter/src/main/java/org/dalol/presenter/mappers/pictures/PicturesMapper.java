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

package org.dalol.presenter.mappers.pictures;

import org.dalol.mezmurmedia.mvp.model.pictures.Cakes;
import org.dalol.mezmurmedia.mvp.model.pictures.PictureInfo;
import org.dalol.mezmurmedia.mvp.model.pictures.PictureModel;
import org.dalol.mezmurmedia.mvp.model.pictures.PictureResponse;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public class PicturesMapper {

    @Inject
    public PicturesMapper() {
    }

    public PictureModel mapResponse(PictureResponse response) {
        PictureModel model = new PictureModel();
        if (response != null) {
            model.setNextLink(response.getNextLink());
            model.setPreviousLink(response.getPreviousLink());
            Cakes[] cakes = response.getCakes();
            if (cakes != null) {
                for (Cakes cake : cakes) {
                    PictureInfo pictureInfo = new PictureInfo(cake.getTitle(), cake.getImage());
                    model.addPicture(pictureInfo);
                }
            }
        }
        return model;
    }
}
