package com.example.dominik.evfinders.application;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {



        Log.d("FCM", "onMessageReceived: " + remoteMessage);
    }
}
