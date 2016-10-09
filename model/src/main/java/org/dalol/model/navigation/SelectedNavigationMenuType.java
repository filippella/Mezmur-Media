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

package org.dalol.model.navigation;

import org.dalol.model.mezmur.MezumrConstants;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/10/2016
 */
public enum SelectedNavigationMenuType {

    MENU_FIND_CHURCHES(MezumrConstants.KEY_FIND_CHURCHES_INDEX),
    MENU_HOLY_PICTURES(MezumrConstants.KEY_HOLY_PICTURES_INDEX),
    MENU_MEZMUR_CHAT_BLOG(MezumrConstants.KEY_MEZMUR_CHAT_BLOG_INDEX),
    MENU_FAVOURITES(MezumrConstants.KEY_FAVOURITES_INDEX),
    MENU_HELP(MezumrConstants.KEY_HELP_INDEX),
    MENU_SETTINGS(MezumrConstants.KEY_SETTINGS_INDEX),
    MENU_SEND_FEEDBACK(MezumrConstants.KEY_EMAIL_INDEX),
    MENU_SHARE(MezumrConstants.KEY_SHARE_INDEX),
    MENU_RATE(MezumrConstants.KEY_RATE_INDEX),
    MENU_ABOUT(MezumrConstants.KEY_ABOUT_INDEX);

    private final int mIndex;

    SelectedNavigationMenuType(@MezumrConstants.NavigationMenuSelection int index) {
        mIndex = index;
    }

    public int getIndex() {
        return mIndex;
    }
}
