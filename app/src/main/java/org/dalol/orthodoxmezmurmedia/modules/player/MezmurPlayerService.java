package org.dalol.orthodoxmezmurmedia.modules.player;

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
public class MezmurPlayerService extends Service {

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
//        if (action.equals("play")) play();

        return START_STICKY;
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
                .addAction(R.mipmap.ic_launcher, "call", getPendingAction("call"))
                .addAction(R.mipmap.ic_launcher, "more", getPendingAction("more"))
                .addAction(R.mipmap.ic_launcher, "add more", getPendingAction("add more"))
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
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
