package org.dalol.orthodoxmezmurmedia.modules.player;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.modules.home.MezmurDashboardActivity;

import java.io.IOException;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/5/2016
 */
public class MezmurPlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener {

    private static final String TAG = MezmurPlayerService.class.getSimpleName();
    private final IBinder mPlayerBinder = new MezmurServiceBinder();

    private static final String CMD_NAME = "command";
    private static final String CMD_PAUSE = "pause";
    private static final String CMD_STOP = "pause";
    private static final String CMD_PLAY = "play";

    // Jellybean
    private static String SERVICE_CMD = "com.sec.android.app.music.musicservicecommand";
    private static String PAUSE_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.pause";
    private static String PLAY_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.play";
    private BroadcastReceiver mIntentReceiver;
    private boolean mReceiverRegistered;

    // Honeycomb
    static {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            SERVICE_CMD = "com.android.music.musicservicecommand";
            PAUSE_SERVICE_CMD = "com.android.music.musicservicecommand.pause";
            PLAY_SERVICE_CMD = "com.android.music.musicservicecommand.play";
        }
    };

    final int NOTIFICATION_ID = 1;
    private MediaPlayer mediaPlayer;
    private NotificationCompat.Builder mNotificationBuilder;
    private boolean mAudioFocusGranted;
    private boolean mAudioIsPlaying;

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

        //createPlayerIfNeeded();

        //play();

        //when neccessary
//            mediaPlayer.reset();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
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

    private void pausePlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            stopForeground(true);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.cancel(NOTIFICATION_ID);
            //notificationManager.cancel(NOTIFICATION_ID);
        }
    }

    private void createPlayerIfNeeded() {
        if (mediaPlayer == null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("https://raw.githubusercontent.com/filippella/LinksForApp/master/mezmur2.mp3");
                mediaPlayer.setLooping(false);
                mediaPlayer.prepareAsync();
                //You can show progress dialog here untill it prepared to play
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //Now dismis progress dialog, Media palyer will start playing
                        showNotification("Started");
                        mp.start();
                    }
                });
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Toast.makeText(MezmurPlayerService.this, "Some Error Occurred!", Toast.LENGTH_SHORT).show();
                        // dissmiss progress bar here. It will come here when MediaPlayer
                        //  is not able to play file. You can show error message to user
                        return false;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mediaPlayer.reset();
        }
    }

    void showNotification(String text) {

        Intent playerIntent = new Intent(this, MezmursPlayerActivity.class);
        Intent dashboardIntent = new Intent(getApplicationContext(), MezmurDashboardActivity.class);

        PendingIntent pi = TaskStackBuilder.create(getApplicationContext())
                .addNextIntentWithParentStack(dashboardIntent)
                .addNextIntent(playerIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotificationBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.ic_call_white_24dp, "play", getPendingAction("play"))
                .addAction(R.mipmap.ic_play_arrow_white_24dp, "pause", getPendingAction("pause"))
                .addAction(R.mipmap.ic_album, "stop", getPendingAction("stop"))
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setTicker(text)
                .setWhen(System.currentTimeMillis())
                .setContentText(text)
                .setContentIntent(pi)
                .setOngoing(true);
        startForeground(NOTIFICATION_ID, mNotificationBuilder.build());
    }

    private PendingIntent getPendingAction(String action) {
        Intent intent = new Intent(getApplicationContext(), MezmurPlayerService.class);
        intent.setAction(action);
        return PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        if (mediaPlayer != null) {
            stopPlaying();
            mediaPlayer = null;
        }
    }

    private void stopPlaying() {
        if (mediaPlayer == null) {
            return;
        }
        try {
            mediaPlayer.reset();
            mediaPlayer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    public void play() {
        if (!mAudioIsPlaying) {
            if (mediaPlayer == null) {
                createPlayerIfNeeded();
            }

            // 1. Acquire audio focus
            if (!mAudioFocusGranted && requestAudioFocus()) {
                // 2. Kill off any other play back sources
                forceMusicStop();
                // 3. Register broadcast receiver for player intents
                setupBroadcastReceiver();
            }
            // 4. Play music
            playMezmur();
            mAudioIsPlaying = true;
        }
    }

    private void playMezmur() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            showNotification("Started");
        }
    }

    public void pause() {
        // 1. Suspend play back
        if (mAudioFocusGranted && mAudioIsPlaying) {
            pausePlaying();
            mAudioIsPlaying = false;
        }
    }

    private void setupBroadcastReceiver() {
        mIntentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String cmd = intent.getStringExtra(CMD_NAME);
                Log.i(TAG, "mIntentReceiver.onReceive " + action + " / " + cmd);

                if (PAUSE_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && CMD_PAUSE.equals(cmd))) {
                    play();
                }

                if (PLAY_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && CMD_PLAY.equals(cmd))) {
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

    public void stop() {
        // 1. Stop play back
        if (mAudioFocusGranted && mAudioIsPlaying) {
            stopPlaying();
            mAudioIsPlaying = false;
            // 2. Give up audio focus
            abandonAudioFocus();
        }
        stopSelf();
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
            intentToStop.putExtra(CMD_NAME, CMD_STOP);
            sendBroadcast(intentToStop);
        }
    }
}
