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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.application.MezmurApplication;
import org.dalol.orthodoxmezmurmedia.basic.di.components.ApplicationComponent;
import org.dalol.model.callback.OnDialogAccessListener;
import org.dalol.orthodoxmezmurmedia.modules.rate.RateDialog;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.presentation.base.BaseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/21/2016
 */
public abstract class BaseActivity<P extends BasePresenter<? extends BaseView, ?>> extends AppCompatActivity implements OnDialogAccessListener {

    @Nullable @BindView(R.id.toolbar) protected Toolbar mToolbar;
    @Nullable @Inject protected Lazy<P> mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int contentRes = getContentView();
        if (contentRes > 0) {
            setContentView(contentRes);
        }
        ButterKnife.bind(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            int statusBarColor = getStatusBarColor();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && statusBarColor > 0) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
            }
        }

        resolveDependency();
        onViewReady(savedInstanceState, getIntent());

        //showRateDialog();
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void showRateDialog() {
        new RateDialog(this)
                .withTitle("Rate this app")
                .withMessage("If you enjoy using this app, would you mind taking a moment to rate it? It won't take more than a minute. Thank you for your support!")
                .withPositiveButtonLabel("Rate now")
                .withNegativeButtonLabel("No, thanks")
                .withNeutralButtonLabel("Later")
                .showDialog();
    }

    protected void onViewReady(Bundle savedInstanceState, Intent intent) {}

    protected void addFragment(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.add(containerId, fragment);
        transaction.commit();
    }

    protected void replaceFragment(int containerId, Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(containerId, fragment);
        //transaction.addToBackStack(tag);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void replaceFragmentWithCustomAnimation(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out, R.anim.trans_right_in, R.anim.trans_right_out);
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void setViewElevation(View view, float elevation) {
        ViewCompat.setElevation(view, elevation);
    }

    protected void showHome() {
        enableHome();
    }

    private void enableHome() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Show Home up indicator icon with custom drawable resource
     *
     * @param resId
     */
    protected void showHome(int resId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            enableHome();
            actionBar.setHomeAsUpIndicator(resId);
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MezmurApplication) getApplication()).getApplicationComponent();
    }

    @Nullable public P getPresenter() {
        Lazy<P> presenter = this.mPresenter;
        if (presenter != null) {
            return presenter.get();
        }
        return null;
    }

    protected int getStatusBarColor() {
        return 0;
    }

    protected void resolveDependency(){}

    protected abstract int getContentView();

    @Override
    public void onShowDialog(String message, String title) {
        mProgressDialog = createDialog(message);
        mProgressDialog.show();
    }

    @Override
    public void onShowDialog(String message) {
        mProgressDialog = createDialog(message);
        mProgressDialog.show();
    }

    private ProgressDialog createDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setMessage(message);
        }
        return mProgressDialog;
    }

    @Override
    public void onHideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onShowAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setMessage(message)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onShowAlertDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
