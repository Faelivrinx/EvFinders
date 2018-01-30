package com.example.dominik.evfinders.application.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.dominik.evfinders.application.EvApplication;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

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
                PendingIntent.FLAG_ONE_SHOT
        );

        Intent cancelIntent = new Intent();
        cancelIntent.putExtra("username", username);
        cancelIntent.putExtra("notification_id", notifyId);
        cancelIntent.setAction("com.example.dominik.action.cancel");
        PendingIntent cancel = PendingIntent.getBroadcast(
                EvApplication.getApplication(),
                notifyId,
                cancelIntent,
                PendingIntent.FLAG_ONE_SHOT
        );

        Notification.Builder mBuilder = new Notification.Builder(EvApplication.getApplication())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Prośba o dodanie!")
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(detailsPendingIntent)
                .addAction(android.R.drawable.ic_menu_compass, "Akceptuj", detailsPendingIntent)
                .addAction(android.R.drawable.ic_menu_crop, "Odrzuć", cancel);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notifyId, mBuilder.build());
    }
}
