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

package org.dalol.orthodoxmezmurmedia.basic.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.dalol.model.callback.OnDialogAccessListener;
import org.dalol.orthodoxmezmurmedia.application.MezmurApplication;
import org.dalol.orthodoxmezmurmedia.basic.di.components.ApplicationComponent;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.presentation.base.BaseView;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public abstract class BaseFragment<P extends BasePresenter<? extends BaseView, ?>> extends Fragment {

    @Nullable @Inject protected Lazy<P> mPresenter;

    @CallSuper @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() == null) {
            setRetainInstance(true);
        }
        resolveDependencies();
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);
        bindView(view);
        return view;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MezmurApplication) getActivity().getApplication()).getApplicationComponent();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewReady(savedInstanceState);
    }

    protected void onViewReady(Bundle savedInstanceState) {}

    protected void bindView(View view) {}

    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @CallSuper @Override public void onDestroy() {
        super.onDestroy();
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    public void onShowDialog(String message) {
        OnDialogAccessListener dialogAccessListener = (OnDialogAccessListener) getActivity();
        dialogAccessListener.onShowDialog(message);
    }

    public void onHideDialog() {
        OnDialogAccessListener dialogAccessListener = (OnDialogAccessListener) getActivity();
        dialogAccessListener.onHideDialog();
    }

    public void onShowToast(String message) {
        Toast.makeText(getActivity(), "Message: " + message, Toast.LENGTH_SHORT).show();
    }

    @Nullable public P getPresenter() {
        Lazy<P> presenter = this.mPresenter;
        if (presenter != null) {
            return presenter.get();
        }
        return null;
    }

    protected void resolveDependencies(){}

    protected abstract int getContentView();
}
