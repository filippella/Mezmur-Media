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

package org.dalol.presenter.business.chat;

import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.presentation.chat.ChatView;
import org.dalol.presenter.storage.Storage;

import javax.inject.Inject;

import static org.dalol.model.mezmur.MezumrConstants.KEY_USER_AUTHENTICATION_ID;
import static org.dalol.model.mezmur.MezumrConstants.KEY_USER_DRAFT_MESSAGE;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public class ChatPresenter extends BasePresenter<ChatView, Void> {

    @Inject protected Storage mUserStorage;

    @Inject
    public ChatPresenter() {
    }

    public void onUserAuthenticated(String currentUserUid, String email) {
        String savedUserId = mUserStorage.getString(KEY_USER_AUTHENTICATION_ID, null);
        if (isUserLoggedIn()) {
            mUserStorage.putString(KEY_USER_AUTHENTICATION_ID, currentUserUid);
        }
        getView().onOpenChatBlogScreen(currentUserUid, email, createUsernameFromEmail(email));
    }

    public boolean isUserLoggedIn() {
        return  mUserStorage.getString(KEY_USER_AUTHENTICATION_ID, null) == null;
    }

    private String createUsernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    public void onAuthenticationDenied() {
        mUserStorage.remove(KEY_USER_AUTHENTICATION_ID);
        getView().onOpenLoginScreen();
    }

    public void onSaveDraftMessage(String messageDraft) {
        mUserStorage.putString(KEY_USER_DRAFT_MESSAGE, messageDraft);
    }

    public String onRestoreMessageDraft() {
        return mUserStorage.getString(KEY_USER_DRAFT_MESSAGE, "");
    }
}
