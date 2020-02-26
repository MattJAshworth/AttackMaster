package xyz.mattjashworth.android.rewardmaster;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by mattjashworth on 27/03/2019.
 * For Spin & Coin Master.
 */
public class MyFirebaseInstanceService extends FirebaseMessagingService {

    public MyFirebaseInstanceService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        SharedPreferences prefs = this.getSharedPreferences(
                "xyz.mattjashworth.attackmaster", Context.MODE_PRIVATE);

        if (remoteMessage.getData().containsValue("ENDBETA1")) {
            prefs.edit().putBoolean("checkWin", true).commit();
        }

        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    private void showNotification(String title, String body) {

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "xyz.mattjashworth.android.coinmasterlinks.message";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "notification",
                    NotificationManager.IMPORTANCE_DEFAULT);


            notificationChannel.setDescription("CoinMaster");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());




    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("PUSH", s);
    }
}
