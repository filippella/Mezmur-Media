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

package org.dalol.orthodoxmezmurmedia.modules.player;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.RemoteViews;

import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.modules.home.MezmurDashboardActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/27/2016
 */
public class PlayerNotificationDelegate {

    private static final int NOTIFICATION_ID = 1;

    private NotificationCompat.Builder mNotificationBuilder;

    public void showNotification(MezmurPlayerService service, String text) {

        Intent playerIntent = new Intent(service, MezmursPlayerActivity.class);
        Intent dashboardIntent = new Intent(service, MezmurDashboardActivity.class);

        PendingIntent intent = TaskStackBuilder.create(service)
                .addNextIntentWithParentStack(dashboardIntent)
                .addNextIntent(playerIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(service.getPackageName(), R.layout.player_small_notification);
        views.setOnClickPendingIntent(R.id.player_pause, getPendingAction(service, "pause"));
        views.setOnClickPendingIntent(R.id.player_previous, getPendingAction(service, "previous"));
        views.setOnClickPendingIntent(R.id.player_next, getPendingAction(service, "next"));
        views.setOnClickPendingIntent(R.id.player_close, getPendingAction(service, "stop"));
        views.setTextViewText(R.id.player_song_name, text);
        views.setViewVisibility(R.id.player_progress_bar, View.GONE);

        mNotificationBuilder = new NotificationCompat.Builder(service)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(intent)
                .setOngoing(true)
                .setContent(views);

        service.startForeground(NOTIFICATION_ID, mNotificationBuilder.build());
    }

    private PendingIntent getPendingAction(MezmurPlayerService service, String action) {
        Intent intent = new Intent(service, MezmurPlayerService.class);
        intent.setAction(action);
        return PendingIntent.getService(service, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void hideNotification(MezmurPlayerService service) {
        service.stopForeground(true);
        NotificationManager mNotificationManager = (NotificationManager) service.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
