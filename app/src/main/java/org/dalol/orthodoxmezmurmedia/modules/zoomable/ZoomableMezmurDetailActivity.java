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

package org.dalol.orthodoxmezmurmedia.modules.zoomable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/28/2016
 */
public class ZoomableMezmurDetailActivity extends BaseActivity {

    public static final String KEY_CONTENT_TO_LOAD = "org.dalol.orthodoxmezmurmedia.modules.zoomable.load.CONTENT";
    public static final String KEY_CONTENT_TITLE = "org.dalol.orthodoxmezmurmedia.modules.zoomable.load.TITLE";

    @BindView(R.id.web_view_zoomable_data) protected WebView web_view;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showHome();

        String contentToLoad = intent.getStringExtra(KEY_CONTENT_TO_LOAD);
        setTitle(intent.getStringExtra(KEY_CONTENT_TITLE));

        String html = "<!doctype html><html><head><meta charset=\"UTF-8\">" +
                "<link rel=\"stylesheet\" href=\"style.css\"></head>" +
                "<body><div align=\"center\">" + contentToLoad + "</div></body>" +
                "</html>";

        if (web_view != null) {
            WebSettings settings = web_view.getSettings();


            settings.setTextZoom(settings.getTextZoom() * 3);

            //settings.setTextSize(WebSettings.TextSize.LARGER);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            //settings.setTextZoom(200);
            //settings.setDefaultFontSize(26);
            web_view.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
            //web_view.loadData(html, "text/html", "UTF-8");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_zoom_in:
                return true;
            case R.id.action_zoom_out:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_zoomable_detail;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
