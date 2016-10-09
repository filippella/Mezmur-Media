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

package org.dalol.orthodoxmezmurmedia.modules.pictures;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class ViewPictureActivity extends BaseActivity {

    public static final String PICTURE_URL = "pictureURL";
    public static final String PICTURE_TITLE = "pictureTitle";

    //@BindView(R.id.imageView) protected SubsamplingScaleImageView mScaleImageView;
    @BindView(R.id.imageView) protected ImageView mImageView;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        showHome();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName("image");
        }
        String title = intent.getStringExtra(PICTURE_TITLE);
        String imageURL = intent.getStringExtra(PICTURE_URL);

        setTitle(title);

        Glide.with(this)
                .load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
        //mScaleImageView.setImage(ImageSource.resource(R.drawable.begena));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_view_picture;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }
}
