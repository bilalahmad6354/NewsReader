package com.example.newsreader.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import com.example.newsreader.R;
import com.example.newsreader.WebViewActivity;
import com.example.newsreader.common.HelperVariables;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class PushNotificationService extends FirebaseMessagingService {

    private String title = "";
    private String body = "";
    private String link = "";
    private String KEY_TITLE = "title";
    private String KEY_BODY = "body";
    private String KEY_LINK = "link";
    private Intent intent;
    private PendingIntent pendingIntent;



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        initializeVariables(remoteMessage);
        intent.putExtra(HelperVariables.KEY_URL_ARGUMENT, link);
        showNotification(getBaseContext(),title, body, intent);
        super.onMessageReceived(remoteMessage);
    }


    private void initializeVariables(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        title = data.get(KEY_TITLE);
        body = data.get(KEY_BODY);
        link = data.get(KEY_LINK);
        intent = new Intent(this, WebViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

    }


    public void showNotification(Context context, String title, String body, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        String channelId = getString(R.string.notification_channel_id);
        String channelName = getString(R.string.notification_channel_name);
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify(notificationId, mBuilder.build());
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }


}
