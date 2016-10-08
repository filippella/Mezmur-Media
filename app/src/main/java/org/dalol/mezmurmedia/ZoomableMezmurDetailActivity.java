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

package org.dalol.mezmurmedia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.dalol.mezmurmedia.business.base.BaseActivity;
import org.dalol.mezmurmedia.modules.details.MezmurDetailActivity;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/28/2016
 */
public class ZoomableMezmurDetailActivity extends BaseActivity {

    @BindView(R.id.web_view_zoomable_data) protected WebView web_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mezmur_detail = getIntent().getStringExtra(MezmurDetailActivity.MEZMUR_DETAIL);

        String html = "<!doctype html><html><head><meta charset=\"UTF-8\">" +
                "<link rel=\"stylesheet\" href=\"style.css\"></head><body><div align=\"center\"><pre>"+mezmur_detail+"</pre></div></body></html>";

        //String head = "<head><style>@font-face {font-family: 'verdana';src: url('file://"+ getFilesDir().getAbsolutePath()+ "/verdana.ttf');}body {font-family: 'verdana';}</style></head>";
        //String htmlData= "<html>"+head+"<body>"+data+"</body></html>" ;

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
            web_view.loadDataWithBaseURL(null,html, "text/html", "UTF-8", null);
            //web_view.loadData(html, "text/html", "UTF-8");
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_zoomable_detail;
    }

    @Override
    protected void resolveDependency() {

    }
}
