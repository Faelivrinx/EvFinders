package com.example.dominik.evfinders.application.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Dominik on 12.09.2017.
 */

public class FCMFriendService extends Service implements FcmFriendContract.View{

    @Inject FcmFriendPresenter presenter;

    public static final String KEY_NEXT = "com.example.dominik.action.friend";
    private FriendReceiver receiver = new FriendReceiver();

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
        presenter.attach(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(KEY_NEXT);
        registerReceiver(receiver, intentFilter);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        presenter.detach();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void closeNotification(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(1);
    }

    public class FriendReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String username = intent.getStringExtra("username");
            Log.d("RECEIVE", "onReceive: " + username);

            presenter.addFriend(username, context);
//            Observable<Response<Friend>> responseObservable = repository.addFriends(username);
        }
    }
}
