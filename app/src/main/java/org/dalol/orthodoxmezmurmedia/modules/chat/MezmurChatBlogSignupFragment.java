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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.business.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.mvp.model.callback.FragmentChatBlogActionListener;
import org.dalol.orthodoxmezmurmedia.mvp.model.callback.OnDialogAccessListener;
import org.dalol.orthodoxmezmurmedia.utilities.helpers.FormValidationHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public class MezmurChatBlogSignupFragment extends BaseFragment {

    @BindView(R.id.registerButton) protected Button mRegisterButton;
    @BindView(R.id.cancelRegistrationButton) protected Button mCancelRegisterButton;
    @BindView(R.id.userFullNameRegister) protected EditText mUserFullName;
    @BindView(R.id.userEmailRegister) protected EditText mUserEmail;
    @BindView(R.id.userPasswordRegister) protected EditText mUserPassword;

    public static MezmurChatBlogSignupFragment newInstance() {
        Bundle args = new Bundle();
        MezmurChatBlogSignupFragment fragment = new MezmurChatBlogSignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_chat_blog_signup;
    }

    @OnClick(R.id.cancelRegistrationButton)
    void onCancelButtonClicked() {
        FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
        actionListener.onActionPerformed(FragmentChatBlogActionListener.PerformedActionType.ACTION_PROCEED_TO_LOGIN_SCREEN);
    }

    @OnClick(R.id.registerButton)
    void onRegisterButtonClicked() {
        if (!FormValidationHelper.validateFields(mUserEmail, mUserPassword)) {
            return;
        }
        createAccount();
    }

    private void createAccount() {
        getDialogAccessListener().onShowDialog("Creating Account...");
        String email = mUserEmail.getText().toString();
        String password = mUserPassword.getText().toString();
        FragmentChatBlogActionListener listener = (FragmentChatBlogActionListener) getActivity();
//        listener.getAuth().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        getDialogAccessListener().onHideDialog();
//                        if (task.isSuccessful()) {
//                            FragmentChatBlogActionListener actionListener = (FragmentChatBlogActionListener) getActivity();
//                            actionListener.onAuthSuccess(task.getResult().getUser());
//                        } else {
//                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    private OnDialogAccessListener getDialogAccessListener() {
        return (OnDialogAccessListener) getActivity();
    }
}
