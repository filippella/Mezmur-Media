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
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.basic.adapter.MessageChatAdapter;
import org.dalol.model.callback.FragmentChatBlogActionListener;
import org.dalol.model.callback.OnDialogAccessListener;
import org.dalol.model.chat.MessageChatModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public class MezmurChatBlogMessagesFragment extends BaseFragment {

    @BindView(R.id.chat_recycler_view) protected RecyclerView mChatList;
    @BindView(R.id.chat_user_message1) protected EditText mChatMessageToSend;

    private MessageChatAdapter mMessageChatAdapter;
    private String mUserId;
    private String mUserDisplayName;
    private String mUserEmail;
    private String mRestoredMessage;
//
//    public static MezmurChatBlogMessagesFragment newInstance(FirebaseUser currentUser, String restoredMessageDraft) {
//        Bundle args = new Bundle();
//        args.putString(KEY_USER_DISPLAY_NAME, currentUser.getDisplayName());
//        args.putString(KEY_USER_EMAIL, currentUser.getEmail());
//        args.putString(KEY_USER_ID, currentUser.getUid());
//        args.putString(KEY_USER_RESTORED_MESSAGE, restoredMessageDraft);
//        MezmurChatBlogMessagesFragment fragment = new MezmurChatBlogMessagesFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
//        mUserId = arguments.getString(KEY_USER_ID);
//        if (mUserId == null) {
//            doLogout();
//        }
//        mUserEmail = arguments.getString(KEY_USER_EMAIL);
//        mUserDisplayName = arguments.getString(KEY_USER_DISPLAY_NAME);
//        mRestoredMessage = arguments.getString(KEY_USER_RESTORED_MESSAGE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chat_blog, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_chat_blog_logout:
                doLogout();
                Toast.makeText(getActivity(), "Logout!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doLogout() {
        FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
        actionListener.onLogoutClicked();
        //actionListener.getAuth().signOut();
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        mChatMessageToSend.setText(mRestoredMessage);
        //mFirebaseChatRef = new Firebase(FIREBASE_CHAT_URL).child("filippo");
        mChatList.setHasFixedSize(true);
        mChatList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        mMessageChatAdapter = new MessageChatAdapter(new ArrayList<MessageChatModel>());
        mChatList.setAdapter(mMessageChatAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_mezmur_chat_blog;
    }

    @OnClick(R.id.sendUserMessage1)
    void sendMessage() {
        String senderMessage = mChatMessageToSend.getText().toString();
        senderMessage = senderMessage.trim();

        if (!senderMessage.isEmpty()) {
            MessageChatModel newChatModel = new MessageChatModel(mUserId, senderMessage, mUserEmail, mUserDisplayName);
           // mFirebaseChatRef.push().setValue(newChatModel);
            mChatMessageToSend.setText("");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
        //FirebaseUser currentUser = actionListener.getAuth().getCurrentUser();
//        if (currentUser == null) {
//            actionListener.onShowLoginScreen();
//        }
        OnDialogAccessListener accessListener = (OnDialogAccessListener) getActivity();
        accessListener.onShowDialog("Loading Mezmur Blog Conversation...");
//        mMessageChatListener = mFirebaseChatRef.limitToFirst(50).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
//
//                OnDialogAccessListener accessListener = (OnDialogAccessListener) getActivity();
//                if (accessListener != null) {
//                    accessListener.onHideDialog();
//                }
//
//
//                if(dataSnapshot.exists()){
//                    // Log.e(TAG, "A new chat was inserted");
//
////                    for (DataSnapshot child: dataSnapshot.getChildren()) {
////                        Toast.makeText(ChatActivity.this, "Message -> " + child.getValue().toString(), Toast.LENGTH_SHORT).show();
////                    }
//
//                    MessageChatModel newMessage = dataSnapshot.getValue(MessageChatModel.class);
//
//                    if(newMessage.getAuthor().equals(mUserId)){
//                        newMessage.setSender(true);
//                    }
//                    mMessageChatAdapter.refillAdapter(newMessage);
//                    mChatList.scrollToPosition(mMessageChatAdapter.getItemCount()-1);
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
    }

    @Override
    public void onStop() {
        super.onStop();
        FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
        actionListener.onSaveEditedMessage(mChatMessageToSend.getText().toString());
        // Remove listener
//        if(mMessageChatListener !=null) {
//            // Remove listener
//            mFirebaseChatRef.removeEventListener(mMessageChatListener);
//        }
        // Clean chat message
          mMessageChatAdapter.cleanUp();
    }
}
