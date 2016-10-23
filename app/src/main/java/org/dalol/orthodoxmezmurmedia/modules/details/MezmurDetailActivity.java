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

package org.dalol.orthodoxmezmurmedia.modules.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.basic.adapter.OMMPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/28/2016
 */
public class MezmurDetailActivity extends BaseActivity {

    public static final String MEZMUR_DETAIL = "mezmur_detail";
    public static final String MEZMUR_TITLE = "mezmur_title";
    public static final String MEZMUR_COLOR_CODE = "color_code";

    private float textSize = 15f;
    @BindView(R.id.adView) protected AdView mAdView;
    @BindView(R.id.viewpager) protected ViewPager mViewPager;
    @BindView(R.id.image_button_add_to_favourite) protected ImageButton mAddToFav;
    @BindView(R.id.image_button_content_copy) protected ImageButton mCopyLyrics;
    @BindView(R.id.image_button_share_lyrics) protected ImageButton mShareLyrics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        showHome();

        Intent intent = getIntent();
        String mezmur_title = intent.getStringExtra(MEZMUR_TITLE);
        String mezmur_detail = intent.getStringExtra(MEZMUR_DETAIL);
        int colorCode = intent.getIntExtra(MEZMUR_COLOR_CODE, 0);


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5489846298805329~3437486295");

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                int visibility = mAdView.getVisibility();
                if(visibility != View.VISIBLE) mAdView.setVisibility(View.VISIBLE);
            }
        });

        mAdView.loadAd(new AdRequest.Builder().build());

//
//        ((GradientDrawable)mBottomTabLayout.getBackground()).setColor(colorCode);


        OMMPagerAdapter pagerAdapter = new OMMPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        pagerAdapter.addFragment(MezmurDetailFragment.newInstance(mezmur_detail), mezmur_title);
        mViewPager.setAdapter(pagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //mAdView.loadAd(new AdRequest.Builder().build());
            }
        });

        //mBottomTabLayout.setBackgroundColor(colorCode);
        //mMezmurDetail.setText(mezmur_detail);
        setTitle("#45 - "+mezmur_title);

        mAddToFav.setColorFilter(ContextCompat.getColor(this, R.color.good_red));
    }

    @OnClick(R.id.image_button_share_lyrics)
    void shareLyrics() {
        Toast.makeText(MezmurDetailActivity.this, "Share Mezmur", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.image_button_content_copy)
    void copyContent() {
        Toast.makeText(MezmurDetailActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.image_button_add_to_favourite)
    void addToFavs() {
        Toast.makeText(MezmurDetailActivity.this, "Added to favourite list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_zoom_in:
                //mMezmurDetail.setTextSize(textSize += 1);
                return true;
            case R.id.action_zoom_out:
                if(textSize == 15) {
                    return false;
                }
                //mMezmurDetail.setTextSize(textSize -= 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void resolveDependency() {

    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
