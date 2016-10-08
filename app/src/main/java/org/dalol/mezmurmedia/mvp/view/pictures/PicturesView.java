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

package org.dalol.mezmurmedia.mvp.view.pictures;

import org.dalol.mezmurmedia.mvp.model.pictures.PictureModel;
import org.dalol.mezmurmedia.mvp.view.base.BaseView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/2/2016
 */
public interface PicturesView extends BaseView {

    /**
     * This method is responsible to bind the response model into the adapter to be shown on the list
     *
     * @param pictureModel
     */
    void onLoadPictures(PictureModel pictureModel);
}
