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

package org.dalol.presenter.business.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import org.dalol.model.mezmur.MezumrConstants;
import org.dalol.presenter.business.base.BasePresenter;
import org.dalol.presenter.presentation.dashboard.DashboardView;

import javax.inject.Inject;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class DashboardPresenter extends BasePresenter<DashboardView, Void> {

//    @Inject protected ResourceProvider mResourceProvider;

    @Inject
    public DashboardPresenter() {
    }

//    public int getTitle() {
//        return mResourceProvider.getHomeTitle();
//    }
//
//    public String getRecentMezmurTitle() {
//        return mResourceProvider.getRecentMezmurTitle();
//    }
//
//    public String getDashboardViewTitle() {
//        return mResourceProvider.getDashboardViewTitle();
//    }

    public void showAbout() {
        getView().onShowAbout();
    }
//
    public void rateApp() {
        String url;
        try {
            getView().getPackageManager().getPackageInfo("com.android.vending", 0);
            url = "market://details?id=" + MezumrConstants.PACKAGE_NAME;
        } catch (final Exception e) {
            url = MezumrConstants.LINK_TO_APP;
        }
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        getView().startActivity(intent);
    }

    public void share() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hey check out Orthodox Mezmur Lyrics App at: https://play.google.com/store/apps/details?id=" + MezumrConstants.PACKAGE_NAME;
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Orthodox Mezmur Lyrics Download Link");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        getView().startActivity(sharingIntent);
    }

    public void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"filippo.eng@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getView().startActivity(Intent.createChooser(emailIntent, "Send Email Message.."));
    }

    public void seeCode() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(MezumrConstants.GITHUB_LINK));
        getView().startActivity(intent);
    }

    public void openSettings() {
        getView().onOpenSettings();
    }
}
