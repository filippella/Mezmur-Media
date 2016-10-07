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

package org.dalol.orthodoxmezmurmedia.modules.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.business.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.business.di.components.DaggerChatComponent;
import org.dalol.orthodoxmezmurmedia.business.di.modules.ChatModule;
import org.dalol.orthodoxmezmurmedia.mvp.model.callback.FragmentChatBlogActionListener;
import org.dalol.orthodoxmezmurmedia.mvp.model.user.User;
import org.dalol.orthodoxmezmurmedia.mvp.presenter.chat.ChatPresenter;
import org.dalol.orthodoxmezmurmedia.mvp.view.chat.ChatView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/10/2016
 */
public class ChatActivity extends BaseActivity<ChatPresenter> implements ChatView, FragmentChatBlogActionListener {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener mOnAuthStateChanged;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
        setTitle("Mezmur Blog Chat");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void resolveDependency() {
        DaggerChatComponent.builder()
        .applicationComponent(getApplicationComponent())
        .chatModule(new ChatModule(this))
        .build().inject(this);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_chat;
    }

    @Override
    public void onStart() {
        super.onStart();
        //checkUserAuthentication("onStart", mAuth);
        mOnAuthStateChanged = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                checkUserAuthentication("onAuthStateChanged", firebaseAuth);
            }
        };
        mAuth.addAuthStateListener(mOnAuthStateChanged);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mOnAuthStateChanged);
    }

    private void checkUserAuthentication(String source, FirebaseAuth firebaseAuth) {
        onHideDialog();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            getPresenter().onUserAuthenticated(currentUser.getUid(), currentUser.getEmail());
        } else {
            getPresenter().onAuthenticationDenied();
        }
//        Toast.makeText(ChatActivity.this, "User Authenticated -> {source="+source+"} " + (currentUser != null), Toast.LENGTH_SHORT).show();
//        if (currentUser != null) {
//            //Toast.makeText(ChatActivity.this, "User already Logged in! " + mCount, Toast.LENGTH_SHORT).show();
//            onAuthSuccess(currentUser);
//        } else {
//            MezmurChatBlogLoginFragment loginFragment = MezmurChatBlogLoginFragment.newInstance();
//            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                replaceFragment(R.id.chat_blog_content, loginFragment);
//            } else {
//                addFragment(R.id.chat_blog_content, loginFragment);
//            }
//        }
    }

    @Override
    public void onActionPerformed(PerformedActionType performedActionType) {
        switch (performedActionType) {
            case ACTION_PROCEED_TO_LOGIN_SCREEN:
                onBackPressed();
                break;
            case ACTION_PROCEED_TO_REGISTRATION_SCREEN:
                MezmurChatBlogSignupFragment signupFragment = MezmurChatBlogSignupFragment.newInstance();
                replaceFragment(R.id.chat_blog_content, signupFragment);
                break;
//            case ACTION_PROCEED_TO_BLOG_SCREEN:
//                MezmurChatBlogMessagesFragment blogFragment = MezmurChatBlogMessagesFragment.newInstance(mAuth.getCurrentUser());
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                    replaceFragment(R.id.chat_blog_content, blogFragment);
//                } else {
//                    addFragment(R.id.chat_blog_content, blogFragment);
//                }
//                break;
        }
    }

    @Override
    public void onAuthSuccess(FirebaseUser user) {
//        String username = usernameFromEmail(user.getEmail());
//        manageUser(user.getUid(), username, user.getEmail());
//        onActionPerformed(FragmentChatBlogActionListener.PerformedActionType.ACTION_PROCEED_TO_BLOG_SCREEN);
    }

    @Override
    public FirebaseAuth getAuth() {
        return mAuth;
    }

    @Override
    public void onLogoutClicked() {
        onShowDialog("Logging Out...");
    }

    @Override
    public void onShowLoginScreen() {
        //clearBackStack();
        getPresenter().onAuthenticationDenied();
    }

    @Override
    public void onSaveEditedMessage(String messageDraft) {
        getPresenter().onSaveDraftMessage(messageDraft);
    }

    @Override
    public void onBackPressed() {
//        if(getPresenter().isUserLoggedIn()) {
//            super.onBackPressed();
//        } else
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onOpenChatBlogScreen(String currentUserUid, String email, String userName) {
        User user = new User(userName, email);
        mDatabase.child("users").child(currentUserUid).setValue(user);
        MezmurChatBlogMessagesFragment blogFragment = MezmurChatBlogMessagesFragment.newInstance(mAuth.getCurrentUser(),
                getPresenter().onRestoreMessageDraft());
        //clearBackStack();
        replaceFragment(R.id.chat_blog_content, blogFragment);
//        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            addFragment(R.id.chat_blog_content, blogFragment);
//        } else {
//            replaceFragmentWithCustomAnimation(R.id.chat_blog_content, blogFragment, R.anim.pop_enter, R.anim.pop_exit);
//        }
    }

    @Override
    public void onOpenLoginScreen() {
        MezmurChatBlogLoginFragment loginFragment = MezmurChatBlogLoginFragment.newInstance();
        //clearBackStack();
        replaceFragment(R.id.chat_blog_content, loginFragment);
    }

    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
            manager.popBackStack();
        }
    }
}
