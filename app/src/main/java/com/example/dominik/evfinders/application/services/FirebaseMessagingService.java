package com.example.dominik.evfinders.application.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.example.dominik.evfinders.application.EvApplication;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private int notifyId = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        createNorification(remoteMessage.getData().get("message"));
    }

    private void createNorification(String message) {
        String [] arrayMessage = message.split(" ");
        String username = arrayMessage[0];

        Intent detailsIntent = new Intent();
        detailsIntent.putExtra("username", username);
        detailsIntent.putExtra("notification_id", notifyId);
        detailsIntent.setAction("com.example.dominik.action.friend");
        PendingIntent detailsPendingIntent = PendingIntent.getBroadcast(
                EvApplication.getApplication(),
                notifyId,
                detailsIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification.Builder mBuilder = new Notification.Builder(EvApplication.getApplication())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Something important happened")
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(detailsPendingIntent)
                .addAction(android.R.drawable.ic_menu_compass, "Accept", detailsPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notifyId, mBuilder.build());
    }
}
