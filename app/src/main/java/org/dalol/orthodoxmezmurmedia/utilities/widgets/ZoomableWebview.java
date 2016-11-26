/*
 * Copyright (c) 2016 Orthodox Mezmur Media
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.utilities.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/26/2016
 */
public class ZoomableWebView extends WebView {

    private static final String ENCLOSING_HTML = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\">" +
            "<link rel=\"stylesheet\" href=\"style.css\"></head>" +
            "<body><div align=\"center\">%s</div></body>" +
            "</html>";

    public ZoomableWebView(Context context) {
        super(context);
        initialize(context);
    }

    public ZoomableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ZoomableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZoomableWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    public ZoomableWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        initialize(context);
    }

    private void initialize(Context context) {
        WebSettings settings = getSettings();
        settings.setTextZoom((int) (settings.getTextZoom() * (2.5f)));

        //settings.setTextSize(WebSettings.TextSize.LARGER);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        //settings.setTextZoom(200);
        //settings.setDefaultFontSize(26);
    }

    /**
     * This method takes a string to be diplayed in the zoomable area
     *
     * @param content
     */
    public void setContent(String content) {
        if (content != null) {
            loadDataWithBaseURL(null, String.format(ENCLOSING_HTML, content), "text/html", "UTF-8", null);
        }
    }
}
