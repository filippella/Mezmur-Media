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

package org.dalol.model.mezmur;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public final class MezumrConstants {

    public static final String FIREBASE_CHAT_URL = "https://inner-doodad-117416.firebaseio.com/mezmur";
    public static final String KEY_USER_DISPLAY_NAME = "user_display_name";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_AUTHENTICATION_ID = "user_authentication_id";
    public static final String KEY_USER_DRAFT_MESSAGE = "user_draft_message";
    public static final String KEY_USER_RESTORED_MESSAGE = "user_restored_message";
    public static final String KEY_HOLY_BOOK_HEADER = "holy_book_header";
    public static final String KEY_HOLY_BOOK_BODY = "holy_book_body";

    private MezumrConstants() {
        throw new AssertionError("Constant class cannot be instantiated!");
    }

    public static final String PACKAGE_NAME = "org.dalol.amharicmezmurlyrics";

    public static final String DASHBOARD_FRAGMENT_TYPE = PACKAGE_NAME + ".dashboard:fragment:type";
    public static final int MEZMUR_CATEGORY_FRAGMENT = 0;
    public static final int RECENT_FRAGMENT = 1;

    public static final String LINK_TO_APP = "https://play.google.com/store/apps/details?id=" + PACKAGE_NAME;
    public static final String GITHUB_LINK = "https://github.com/filippella/ApkDigger";


    @IntDef(flag = true , value = {KEY_FIND_CHURCHES_INDEX, KEY_HOLY_PICTURES_INDEX, KEY_MEZMUR_CHAT_BLOG_INDEX
            , KEY_FAVOURITES_INDEX, KEY_HELP_INDEX, KEY_SETTINGS_INDEX
            , KEY_EMAIL_INDEX, KEY_SHARE_INDEX, KEY_RATE_INDEX, KEY_ABOUT_INDEX})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NavigationMenuSelection {}

    public static final int KEY_FIND_CHURCHES_INDEX = 0;
    public static final int KEY_HOLY_PICTURES_INDEX = 1;
    public static final int KEY_MEZMUR_CHAT_BLOG_INDEX = 2;
    public static final int KEY_FAVOURITES_INDEX = 3;
    public static final int KEY_HELP_INDEX = 4;
    public static final int KEY_SETTINGS_INDEX = 5;
    public static final int KEY_EMAIL_INDEX = 6;
    public static final int KEY_SHARE_INDEX = 7;
    public static final int KEY_RATE_INDEX = 8;
    public static final int KEY_ABOUT_INDEX = 9;
}
