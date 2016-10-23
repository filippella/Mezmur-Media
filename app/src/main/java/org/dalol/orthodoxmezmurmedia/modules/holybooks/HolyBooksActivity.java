package org.dalol.orthodoxmezmurmedia.modules.holybooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.dalol.model.books.HolyContent;
import org.dalol.model.books.HolyContentBook;
import org.dalol.model.callback.OnZoomActionListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.adapter.OMMPagerAdapter;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;
import org.dalol.orthodoxmezmurmedia.basic.di.components.DaggerHolyBookComponent;
import org.dalol.orthodoxmezmurmedia.basic.di.modules.HolyBookModule;
import org.dalol.presenter.business.books.HolyBookPresenter;
import org.dalol.presenter.presentation.books.HolyBookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyBooksActivity extends BaseActivity<HolyBookPresenter> implements HolyBookView {

    @BindView(R.id.view_pager) protected ViewPager mBooksPager;
    @BindView(R.id.tab_layout) protected TabLayout mTabLayout;
    @BindView(R.id.adView) protected AdView mAdView;

    private OMMPagerAdapter mPagerAdapter;
    private List<OnZoomActionListener> mListeners = new ArrayList<>();

    @Override
    protected void resolveDependency() {
        super.resolveDependency();
        DaggerHolyBookComponent.builder()
                .applicationComponent(getApplicationComponent())
                .holyBookModule(new HolyBookModule(this, "wuddasie_amlak.json"))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Holy Books");

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

        mPagerAdapter = new OMMPagerAdapter(getSupportFragmentManager());
        mBooksPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mBooksPager);
        getPresenter().onViewReady();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_holy_books;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zooming, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_zoom_in:
                notifyZoomIn();
                return true;
            case R.id.action_zoom_out:
                notifyZoomOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void notifyZoomIn() {
        for (OnZoomActionListener listener : mListeners) {
            listener.onZoomIn();
        }
    }

    private void notifyZoomOut() {
        for (OnZoomActionListener listener : mListeners) {
            listener.onZoomOut();
        }
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    @Override
    public void onLoadContent(HolyContentBook contentBook) {
        List<HolyContent> contents = contentBook.getContents();
        setTitle(contentBook.getBookName());
        if (contents != null) {
            int size = contents.size();
            mBooksPager.setOffscreenPageLimit(size);
            for (int i = 0; i < size; i++) {
                HolyContent holyContent = contents.get(i);
                int id = holyContent.getId();
                String title = holyContent.getTitle();
                String header = holyContent.getHeader();
                String body = holyContent.getBody();
                HolyBookFragment fragment = HolyBookFragment.newInstance(header, body);
                mPagerAdapter.addFragment(fragment, title);
            }
        }
        mPagerAdapter.notifyDataSetChanged();
        onHideDialog();
    }

    /**
     * This method is used to add a zoom listener
     *
     * @param listener
     */
    public void addZoomListener(OnZoomActionListener listener) {
        mListeners.add(listener);
    }

    /**
     * This method is used to remove zoom listener
     *
     * @param listener
     */
    public void removeZoomListener(OnZoomActionListener listener) {
        mListeners.add(listener);
    }
}
