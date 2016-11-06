package org.dalol.orthodoxmezmurmedia.modules.player;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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

    final int NOTIFICATION_ID = 1;
    private MediaPlayer mediaPlayer;
    private NotificationCompat.Builder mNotificationBuilder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createPlayerIfNeeded();

        //when neccessary
//            mediaPlayer.reset();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action == null) {
            return START_STICKY;
        }
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
        //createPlayerIfNeeded();
        if (action.equals("play")) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                showNotification("Started");
            }
        } else if (action.equals("pause")) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                stopForeground(true);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.cancel(NOTIFICATION_ID);
                //notificationManager.cancel(NOTIFICATION_ID);
            }
        } else if (action.equals("stop")) {
            stopPlaying();
        }

        return START_STICKY;
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

        Intent playerIntent = new Intent(this, MezmursPlayer.class);
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
}
