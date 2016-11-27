package org.dalol.orthodoxmezmurmedia.modules.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.dalol.model.callback.OnPlayerMenuClickListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class MezmursPlayerActivity extends BaseActivity implements OnPlayerMenuClickListener, MezmurPlayerService.OnMezmurMediaPlayerStateListener {

    @BindView(R.id.mezmur_progress_slider) protected SeekBar mMezmurProgress;
    @BindView(R.id.adView) protected AdView mAdView;
    @BindView(R.id.currentElapsed) protected TextView mElapsedProgress;
    @BindView(R.id.duration) protected TextView mMezmurDuration;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Mezmur Player");

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5489846298805329~3437486295");


        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mMezmurProgress.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        mMezmurProgress.setPadding(0, 0, 0, 0);

        mMezmurProgress.setProgress(88);
        mMezmurProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mService != null) {
                    mService.seekTo(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        MezmurPlayerLyricsFragment fragment = MezmurPlayerLyricsFragment.newInstance();
        replaceFragment(R.id.mezmur_player_content_container, fragment);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                int visibility = mAdView.getVisibility();
                if(visibility != View.VISIBLE) mAdView.setVisibility(View.VISIBLE);
            }
        });

        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @OnClick(R.id.buttonDownloadMezmur)
    void onDownloadButtonClick() {
        Toast.makeText(mService, "Downloading Mezmur", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonFavouriteMezmur)
    void onFavouriteButtonClick() {
        Toast.makeText(mService, "Favorite Mezmur", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonShareMezmur)
    void onShareButtonClick() {
        Toast.makeText(mService, "Sharing Mezmur", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.start_playing_button)
    void onPlayClick() {
        Intent intent = new Intent(this, MezmurPlayerService.class);
        intent.setAction("play");
        startService(intent);
    }

    @OnClick(R.id.stop_playing_button)
    void onStopClick() {
        Intent intent = new Intent(this, MezmurPlayerService.class);
        intent.setAction("stop");
        startService(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mezmurs_player;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "New Intent Detected", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MezmurPlayerService.class);
        startService(intent);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimaryDark;
    }

    private MezmurPlayerService mService;
    private boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            MezmurPlayerService.MezmurServiceBinder binder = (MezmurPlayerService.MezmurServiceBinder) service;
            mService = binder.getService();
            mService.setStateListener(MezmursPlayerActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onShowMezmurListFragment() {
        MezmurListPlayerFragment fragment = MezmurListPlayerFragment.newInstance();
        replaceFragmentWithCustomAnimation(R.id.mezmur_player_content_container, fragment,
                R.anim.slide_in, R.anim.slide_out, R.anim.slide_down, R.anim.slide_down_and_bounce);
    }

    @Override
    public void onShowMezmurDetailFragment() {
        MezmurPlayerLyricsFragment fragment = MezmurPlayerLyricsFragment.newInstance();
        replaceFragmentWithCustomAnimation(R.id.mezmur_player_content_container, fragment,
                R.anim.slide_in, R.anim.slide_out, R.anim.slide_down, R.anim.slide_down_and_bounce);
    }

    @Override
    public void onPlayerStart(int duration, int currentDuration) {
        mMezmurProgress.setMax(duration);
        mMezmurProgress.setProgress(currentDuration);

        mMezmurDuration.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) duration),
                TimeUnit.MILLISECONDS.toSeconds((long) duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                duration)))
        );

        mElapsedProgress.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) currentDuration),
                TimeUnit.MILLISECONDS.toSeconds((long) currentDuration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                currentDuration)))
        );
    }

    @Override
    public void onProgressChanged(int progress, String progressText) {
        mMezmurProgress.setProgress(progress);
        mElapsedProgress.setText(progressText);
    }
}
