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

package org.dalol.mezmurmedia.mvp.model.callback;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public interface FragmentChatBlogActionListener {

    /**
     * This method will be invoked to switching between blocg chat fragments
     *
     * @param performedActionType
     */
    void onActionPerformed(PerformedActionType performedActionType);

    /**
     * When user is leaving the chat session
     */
    void onLogoutClicked();

    /**
     * This method will be invoked to navigate onto the login screen
     */
    void onShowLoginScreen();

    /**
     * The message to be broadcated
     *
     * @param messageDraft
     */
    void onSaveEditedMessage(String messageDraft);


    /**
     * Type of an action to be performed
     */
    enum PerformedActionType {

        /**
         * This field specifies the user action on register button
         */
        ACTION_PROCEED_TO_REGISTRATION_SCREEN,

        /**
         * This field specifies user ignoring to resgiter and proceed to login screen
         */
        ACTION_PROCEED_TO_LOGIN_SCREEN,
    }
}
