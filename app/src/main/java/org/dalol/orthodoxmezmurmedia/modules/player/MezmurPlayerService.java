package org.dalol.orthodoxmezmurmedia.modules.player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/5/2016
 */
public class MezmurPlayerService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private static final String TAG = MezmurPlayerService.class.getSimpleName();
    private final IBinder mPlayerBinder = new MezmurServiceBinder();

    public static final String PLAYER_COMMAND = "command";
    public static final String PLAYER_COMMAND_PAUSE = "pause";
    public static final String PLAYER_COMMAND_STOP = "stop";
    public static final String PLAYER_COMMAND_PLAY = "play";

    // Jellybean
    static String SERVICE_CMD = "com.sec.android.app.music.musicservicecommand";
    static String PAUSE_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.pause";
    static String PLAY_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.play";

    // Honeycomb
    static {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            SERVICE_CMD = "com.android.music.musicservicecommand";
            PAUSE_SERVICE_CMD = "com.android.music.musicservicecommand.pause";
            PLAY_SERVICE_CMD = "com.android.music.musicservicecommand.play";
        }
    }

    private MediaPlayer mediaPlayer;
    private NotificationCompat.Builder mNotificationBuilder;
    private boolean mAudioFocusGranted;
    private boolean mAudioIsPlaying;
    private BroadcastReceiver mIntentReceiver;
    private boolean mReceiverRegistered;

    private PlayerNotificationDelegate mNotificationDelegate;

    public void seekTo(int progress) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(progress);
        }
    }

    public class MezmurServiceBinder extends Binder {
        MezmurPlayerService getService() {
            return MezmurPlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mPlayerBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNotificationDelegate = new PlayerNotificationDelegate();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);


        //Fetch Mezmur here

        //createPlayerIfNeeded();

        //play();

        //when neccessary
//            mediaPlayer.reset();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action;
        if (intent != null && (action = intent.getAction()) != null) {
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
            //createPlayerIfNeeded();
            if (action.equals("play")) {
                play();
            } else if (action.equals("pause")) {
                pause();
            } else if (action.equals("stop")) {
                stop();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mAudioIsPlaying = false;
        Toast.makeText(this, "Finished Playing! Starting next Mezmur!!", Toast.LENGTH_SHORT).show();
        //Play next track
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(MezmurPlayerService.this, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
        mp.reset();
        // dissmiss progress bar here. It will come here when MediaPlayer
        //  is not able to play file. You can show error message to user
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //Now dismis progress dialog, Media palyer will start playing
        if (mp != null && !mp.isPlaying()) {
            mp.start();
            mNotificationDelegate.showNotification(this, "Started");
            mAudioIsPlaying = true;
            if (mStateListener != null) {
                mStateListener.onPlayerStart(mediaPlayer.getDuration(), mediaPlayer.getCurrentPosition());
            }
            myHandler.postDelayed(UpdateSongTime,100);
        }
    }

    public void play() {
        if (!mAudioIsPlaying) {

            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource("http://www.villopim.com.br/android/Music_01.mp3");
                mediaPlayer.setOnPreparedListener(MezmurPlayerService.this);
                mediaPlayer.setOnErrorListener(MezmurPlayerService.this);
                mediaPlayer.setOnCompletionListener(MezmurPlayerService.this);
                mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        Toast.makeText(MezmurPlayerService.this, "Buffering...", Toast.LENGTH_SHORT).show();
                    }
                });
                mediaPlayer.prepareAsync();
                //You can show progress dialog here untill it prepared to play
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 1. Acquire audio focus
            if (!mAudioFocusGranted && requestAudioFocus()) {
                // 2. Kill off any other play back sources
                forceMusicStop();
                // 3. Register broadcast receiver for player intents
                setupBroadcastReceiver();
            }
            // 4. Play music
            //playMezmur();

        }
    }

//    @Override
//    public boolean onUnbind(Intent intent){
//        mediaPlayer.stop();
//        mediaPlayer.release();
//        return false;
//    }

    public void pause() {
        // 1. Suspend play back
        if (mAudioFocusGranted && mAudioIsPlaying) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            mAudioIsPlaying = false;
        }
    }

    public void stop() {
        // 1. Stop play back
        if (mAudioFocusGranted && mAudioIsPlaying) {
            if (mediaPlayer != null) {
                try {
                    mediaPlayer.stop();
                    myHandler.removeCallbacks(UpdateSongTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mAudioIsPlaying = false;
            mNotificationDelegate.hideNotification(this);
            // 2. Give up audio focus
            abandonAudioFocus();
        }
        stopSelf();
    }

    private void setupBroadcastReceiver() {
        mIntentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String cmd = intent.getStringExtra(PLAYER_COMMAND);
                Log.i(TAG, "mIntentReceiver.onReceive " + action + " / " + cmd);

                if (PAUSE_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && PLAYER_COMMAND_PAUSE.equals(cmd))) {
                    play();
                }

                if (PLAY_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && PLAYER_COMMAND_PLAY.equals(cmd))) {
                    pause();
                }
            }
        };

        // Do the right thing when something else tries to play
        if (!mReceiverRegistered) {
            IntentFilter commandFilter = new IntentFilter();
            commandFilter.addAction(SERVICE_CMD);
            commandFilter.addAction(PAUSE_SERVICE_CMD);
            commandFilter.addAction(PLAY_SERVICE_CMD);
            registerReceiver(mIntentReceiver, commandFilter);
            mReceiverRegistered = true;
        }
    }

    private boolean requestAudioFocus() {
        if (!mAudioFocusGranted) {
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            // Request audio focus for play back
            int result = am.requestAudioFocus(mOnAudioFocusChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mAudioFocusGranted = true;
            } else {
                // FAILED
                Log.e(TAG,
                        ">>>>>>>>>>>>> FAILED TO GET AUDIO FOCUS <<<<<<<<<<<<<<<<<<<<<<<<");
            }
        }
        return mAudioFocusGranted;
    }

    private void abandonAudioFocus() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = am.abandonAudioFocus(mOnAudioFocusChangeListener);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mAudioFocusGranted = false;
        } else {
            // FAILED
            Log.e(TAG,
                    ">>>>>>>>>>>>> FAILED TO ABANDON AUDIO FOCUS <<<<<<<<<<<<<<<<<<<<<<<<");
        }
        mOnAudioFocusChangeListener = null;
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.i(TAG, "AUDIOFOCUS_GAIN");
                    play();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                    Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT");
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                    Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.e(TAG, "AUDIOFOCUS_LOSS");
                    pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                    pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                    Log.e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                    break;
                default:
                    //
            }
        }
    };

    private void forceMusicStop() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (am.isMusicActive()) {
            Intent intentToStop = new Intent(SERVICE_CMD);
            intentToStop.putExtra(PLAYER_COMMAND, PLAYER_COMMAND_STOP);
            sendBroadcast(intentToStop);
        }
    }

    private Handler myHandler = new Handler();
    private OnMezmurMediaPlayerStateListener mStateListener;

    public void setStateListener(OnMezmurMediaPlayerStateListener listener) {
        mStateListener = listener;
        if (mStateListener != null) {
            mStateListener.onPlayerStart(mediaPlayer.getDuration(), mediaPlayer.getCurrentPosition());
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (mStateListener != null) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                mStateListener.onProgressChanged(currentPosition, String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) currentPosition),
                        TimeUnit.MILLISECONDS.toSeconds((long) currentPosition) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) currentPosition))));
            }
            myHandler.postDelayed(this, 100);
        }
    };

    public interface OnMezmurMediaPlayerStateListener {

        void onPlayerStart(int duration, int currentDuration);

        void onProgressChanged(int progress, String progressText);
    }
}
