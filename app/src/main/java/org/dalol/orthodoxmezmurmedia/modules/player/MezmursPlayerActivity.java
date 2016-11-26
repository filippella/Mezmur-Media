package org.dalol.orthodoxmezmurmedia.modules.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import org.dalol.model.callback.OnPlayerMenuClickListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public class MezmursPlayerActivity extends BaseActivity implements OnPlayerMenuClickListener {

    @BindView(R.id.mezmur_progress_slider) protected SeekBar mMezmurProgress;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showHome();
        setTitle("Mezmur Player");

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mMezmurProgress.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        mMezmurProgress.setPadding(0, 0, 0, 0);

        mMezmurProgress.setProgress(88);

        MezmurPlayerLyricsFragment fragment = MezmurPlayerLyricsFragment.newInstance();
        replaceFragment(R.id.mezmur_player_content_container, fragment);
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
}
