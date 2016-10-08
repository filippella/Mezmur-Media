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

package org.dalol.mezmurmedia.modules.chat;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.business.base.BaseFragment;
import org.dalol.mezmurmedia.mvp.model.callback.FragmentChatBlogActionListener;
import org.dalol.mezmurmedia.mvp.model.callback.OnDialogAccessListener;
import org.dalol.mezmurmedia.utilities.helpers.AbstractTextChangeTracker;
import org.dalol.mezmurmedia.utilities.helpers.FormValidationHelper;
import org.dalol.mezmurmedia.utilities.helpers.KeyboardHelper;

import butterknife.BindView;
import butterknife.OnClick;

import static org.dalol.mezmurmedia.mvp.model.callback.FragmentChatBlogActionListener.PerformedActionType.ACTION_PROCEED_TO_REGISTRATION_SCREEN;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public class MezmurChatBlogLoginFragment extends BaseFragment {

    @BindView(R.id.loginButton) protected Button mLoginButton;
    @BindView(R.id.registerButton) protected Button mRegisterButton;
    @BindView(R.id.userEmailLogin) protected EditText mUserEmail;
    @BindView(R.id.userPasswordLogin) protected EditText mUserPassword;
    @BindView(R.id.userEmailLoginClearButton) protected ImageView mUserEmailClearButton;
    @BindView(R.id.userPasswordLoginClearButton) protected ImageView mUserPasswordClearButton;

    public static MezmurChatBlogLoginFragment newInstance() {
        Bundle args = new Bundle();
        MezmurChatBlogLoginFragment fragment = new MezmurChatBlogLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        mUserEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mUserPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });
        mUserPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    performLogin(v);
                    return true;
                }
                return false;
            }
        });
        mUserEmail.addTextChangedListener(new AbstractTextChangeTracker() {
            @Override
            protected void onTextValueEmpty(boolean emptyValue) {
                mUserEmailClearButton.setVisibility(emptyValue ? View.GONE: View.VISIBLE);
            }
        });
        mUserPassword.addTextChangedListener(new AbstractTextChangeTracker() {
            @Override
            protected void onTextValueEmpty(boolean emptyValue) {
                mUserPasswordClearButton.setVisibility(emptyValue ? View.GONE: View.VISIBLE);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_chat_blog_login;
    }

    @OnClick(R.id.registerButton)
    void onRegisterButtonClicked() {
        FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
        actionListener.onActionPerformed(ACTION_PROCEED_TO_REGISTRATION_SCREEN);
    }

    @OnClick(R.id.loginButton)
    void onLoginButtonClicked() {
        performLogin(getActivity().getCurrentFocus());
    }

    @OnClick(R.id.userPasswordLoginClearButton)
    void onClearUserPasswordField() {
        mUserPassword.setText(null);
        mUserPassword.requestFocus();
    }

    @OnClick(R.id.userEmailLoginClearButton)
    void onClearUserEmailField() {
        mUserEmail.setText(null);
        mUserEmail.requestFocus();
    }

    private void performLogin(View view) {
        if (!FormValidationHelper.validateFields(mUserEmail, mUserPassword)) {
            return;
        }
        KeyboardHelper.hideSoftKeyboard(getContext(), view);
        signIn();
    }

    private void signIn() {
        getDialogAccessListener().onShowDialog("Logging In...");
        String email = mUserEmail.getText().toString();
        String password = mUserPassword.getText().toString();
        FragmentChatBlogActionListener listener = (FragmentChatBlogActionListener) getActivity();
//        listener.getAuth().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        getDialogAccessListener().onHideDialog();
//
//                        if (task.isSuccessful()) {
//                            FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
//                            actionListener.onAuthSuccess(task.getResult().getUser());
//                        } else {
//                            String message = task.getException().getMessage();
//                            getDialogAccessListener().onShowAlertDialog(message);
//                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    private OnDialogAccessListener getDialogAccessListener() {
        return (OnDialogAccessListener) getActivity();
    }
}