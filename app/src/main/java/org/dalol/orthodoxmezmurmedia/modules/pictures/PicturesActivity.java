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

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerPicturesComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.ApiModule;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.PicturesModule;
import org.dalol.orthodoxmezmurmedia.modules.pictures.adapter.PicturesGridAdapter;
import org.dalol.orthodoxmezmurmedia.utilities.custom.RecyclerGridItemMarginDecorator;
import org.dalol.model.callback.OnPictureOptionSelectListener;
import org.dalol.model.miscellaneous.ItemView;
import org.dalol.model.pictures.PictureInfo;
import org.dalol.model.pictures.PictureModel;
import org.dalol.presenter.business.pictures.PicturesPresenter;
import org.dalol.presenter.presentation.pictures.PicturesView;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class PicturesActivity extends BaseActivity<PicturesPresenter> implements OnPictureOptionSelectListener, PicturesView {

    @BindView(R.id.recycle_view_picture_list) protected RecyclerView mPicturesList;
    private PicturesGridAdapter mPictureAdapter;
    private boolean loading;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        setTitle("Picture Collections");
        showHome();
        GridLayoutManager recycleLayoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recycleLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(mPictureAdapter.getItemViewType(position) == ItemView.ItemViewType.TYPE_PROGRESS_ITEM.getViewType()) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mPicturesList.setLayoutManager(recycleLayoutManager);
        mPicturesList.addItemDecoration(new RecyclerGridItemMarginDecorator(getResources().getDimensionPixelSize(R.dimen.mezmur_dashboard_grid_item_margin_size)));
        mPicturesList.setItemAnimator(new DefaultItemAnimator());
        mPicturesList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public int visibleThreshold = 2;
            public int lastVisibleItem;
            public int totalItemCount;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    //End of the items
                    Toast.makeText(PicturesActivity.this, "Loading more...", Toast.LENGTH_SHORT).show();
                    mPictureAdapter.appendProgressLayout();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getPresenter().onViewReady();
                        }
                    }, 0L);

//                    if (onLoadMoreListener != null) {
//                        onLoadMoreListener.LoadItems();
//                    }
                    loading = true;
                }
            }
        });
        mPictureAdapter = new PicturesGridAdapter(this);
        mPicturesList.setAdapter(mPictureAdapter);
        getPresenter().onViewReady();
    }

    @Override
    protected void resolveDependency() {
        DaggerPicturesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .apiModule(new ApiModule("https://gist.githubusercontent.com"))
                .picturesModule(new PicturesModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pictures, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pictures;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_upload:
                onShowAlertDialog("Upload Picture functionality is not supported yet in this version." +
                        "\nIt will be implemented in the coming versions soon.");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowOption(View view, int position) {
        PopupMenu popup = new PopupMenu(PicturesActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.pictures_popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_open:

                        return true;
                    case R.id.menu_export:

                        return true;
                    case R.id.menu_uninstall:

                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    @Override
    public void onShowImage(PictureInfo pictureInfo, View view) {
        Intent intent = new Intent(PicturesActivity.this, ViewPictureActivity.class);
        intent.putExtra(ViewPictureActivity.PICTURE_TITLE, pictureInfo.getTitle());
        intent.putExtra(ViewPictureActivity.PICTURE_URL, pictureInfo.getImage());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PicturesActivity.this, view, "image");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onLoadPictures(PictureModel pictureModel) {
        loading = false;
        mPictureAdapter.removeProgressLayout();
        mPictureAdapter.addPictures(pictureModel);
    }
}
