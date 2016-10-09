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

package org.dalol.mezmurmedia.modules.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.dalol.mezmurmedia.R;
import org.dalol.mezmurmedia.basic.base.BaseActivity;
import org.dalol.mezmurmedia.basic.di.components.DaggerDashboardComponent;
import org.dalol.mezmurmedia.basic.di.modules.DashboardModule;
import org.dalol.mezmurmedia.basic.adapter.MezmurPagerAdapter;
import org.dalol.mezmurmedia.modules.churches.ChurchListActivity;
import org.dalol.mezmurmedia.modules.favourites.FavouritesActivity;
import org.dalol.mezmurmedia.modules.pictures.PicturesActivity;
import org.dalol.mezmurmedia.utilities.common.CommonUtils;
import org.dalol.model.mezmur.MezumrConstants;
import org.dalol.model.navigation.SelectedNavigationMenuType;
import org.dalol.presenter.business.dashboard.DashboardPresenter;
import org.dalol.presenter.presentation.dashboard.DashboardView;

import butterknife.BindView;

import static org.dalol.model.navigation.SelectedNavigationMenuType.*;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/29/2016
 */
public class MezmurDashboardActivity extends BaseActivity<DashboardPresenter> implements DashboardView {

    public static final long PROCESS_MENU_DELAY_MILLIS = 250L;

    @BindView(R.id.tabs) protected TabLayout mTabLayout;
    @BindView(R.id.viewpager) protected ViewPager mViewPager;
    @BindView(R.id.drawerLayout) protected DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) protected NavigationView mNavigationView;
    private ImageView mNavHeaderImageView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
        setTitle("Test");
        initializeViews();
    }

    private void initializeViews() {
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(mNavigationItemSelectedListener);
        if (CommonUtils.getScreenWidthInDensityPixel(this) <= 360) {
            mNavigationView.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels - CommonUtils.computeTypedValue(this, TypedValue.COMPLEX_UNIT_DIP, 56);
        }

        FrameLayout view = (FrameLayout) mNavigationView.getMenu().findItem(R.id.menu_favourites).getActionView();
        int count = 65;
        TextView txt = (TextView) view.getChildAt(0);
        txt.setText(count > 0 ? String.valueOf(count) : null);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                supportInvalidateOptionsMenu();
            }
        };

        mNavHeaderImageView = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.navigation_view_header_background_image);
        Glide.with(this).load(R.drawable.back_round).into(mNavHeaderImageView);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        MezmurPagerAdapter pagerAdapter = new MezmurPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(DashboardFragment.newInstance(), "Mezmur Cats");
        pagerAdapter.addFragment(OtherMenusFragment.newInstance(), "other");
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if(item.getItemId() == R.id.action_help) {
            mDashboardHandler.sendEmptyMessageDelayed(MENU_HELP.getIndex(), PROCESS_MENU_DELAY_MILLIS);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void resolveDependency() {
        DaggerDashboardComponent.builder()
                .dashboardModule(new DashboardModule(this))
                .build()
                .inject(this);
    }

    private NavigationView.OnNavigationItemSelectedListener mNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            mDrawerLayout.closeDrawers();
            menuItem.setChecked(true);
            switch (menuItem.getItemId()) {
                case R.id.menu_find_churches:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_FIND_CHURCHES.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
                case R.id.menu_holy_pictures:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_HOLY_PICTURES.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
//                case R.id.menu_mezmur_blog:
//                    mDashboardHandler.sendEmptyMessageDelayed(MENU_MEZMUR_CHAT_BLOG.getIndex(), PROCESS_MENU_DELAY_MILLIS);
//                    return true;
                case R.id.menu_favourites:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_FAVOURITES.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
//                case R.id.menu_help:
//                    mDashboardHandler.sendEmptyMessageDelayed(MENU_HELP.getIndex(), PROCESS_MENU_DELAY_MILLIS);
//                    return true;
                case R.id.menu_settings:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_SETTINGS.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
                //Feedback will be inside help
//                case R.id.menu_send_feedback:
//                    mDashboardHandler.sendEmptyMessageDelayed(MENU_SEND_FEEDBACK.getIndex(), PROCESS_MENU_DELAY_MILLIS);
//                    return true;
                case R.id.menu_share:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_SHARE.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
                case R.id.menu_rate:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_RATE.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
                case R.id.menu_about:
                    mDashboardHandler.sendEmptyMessageDelayed(MENU_ABOUT.getIndex(), PROCESS_MENU_DELAY_MILLIS);
                    return true;
                default:
                    Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    };

    private Handler mDashboardHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MezumrConstants.KEY_FIND_CHURCHES_INDEX:
                    startActivity(new Intent(MezmurDashboardActivity.this, ChurchListActivity.class));
                    break;
                case MezumrConstants.KEY_HOLY_PICTURES_INDEX:
                    startActivity(new Intent(MezmurDashboardActivity.this, PicturesActivity.class));
                    break;
                case MezumrConstants.KEY_MEZMUR_CHAT_BLOG_INDEX:
                    getPresenter().openChat();
                    break;
                case MezumrConstants.KEY_FAVOURITES_INDEX:
                    FavouritesActivity.start(MezmurDashboardActivity.this);
                    //getPresenter().showAbout();
                    break;
                case MezumrConstants.KEY_HELP_INDEX:
                    getPresenter().seeCode();
                    break;
                case MezumrConstants.KEY_SETTINGS_INDEX:
                    getPresenter().openSettings();
                    break;
                case MezumrConstants.KEY_EMAIL_INDEX:
                    getPresenter().sendEmail();
                    break;
                case MezumrConstants.KEY_SHARE_INDEX:
                    getPresenter().share();
                    break;
                case MezumrConstants.KEY_RATE_INDEX:
                    getPresenter().rateApp();
                    break;
                case MezumrConstants.KEY_ABOUT_INDEX:
                    getPresenter().showAbout();
                    break;
            }
        }
    };

    @Override
    public Activity getActivity() {
        return MezmurDashboardActivity.this;
    }
}
