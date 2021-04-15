package com.appcare.followconnect.firebase;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SplashScreenActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import androidx.core.app.NotificationCompat;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();


    String CHANNEL_ID = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        // CHANNEL_ID = remoteMessage.getNotification().getChannelId();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sendNotification1(remoteMessage);
        } else {
            sendNotification(remoteMessage);
        }
    }

    @SuppressLint("LongLogTag")
    private void sendNotification(RemoteMessage remoteMessage) {
        if (!isAppIsInBackground(getApplicationContext())) {
            //foreground app
            Log.e("remoteMessage foreground", remoteMessage.getData().toString());
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String timeStamp = "";
            Intent resultIntent = new Intent(getApplicationContext(), HomeActivity.class);
            resultIntent.putExtra("key", remoteMessage.getData().get("type"));
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.follow_connect_logo)
                    .setWhen(getTimeMilliSec(timeStamp))
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                    .setNumber(10)
                    .setTicker("Bestmarts")
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentInfo("Info");
            notificationManager.notify(1, notificationBuilder.build());
        } else {
            Log.e("remoteMessage background", remoteMessage.getData().toString());
            Map data = remoteMessage.getData();
            String title = (String) data.get("title");
            String body = (String) data.get("body");
            String timeStamp = "";
            Intent resultIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
            resultIntent.putExtra("key", remoteMessage.getData().get("type"));
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.follow_connect_logo)
                    .setWhen(getTimeMilliSec(timeStamp))
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                    .setNumber(10)
                    .setTicker("Bestmarts")
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentInfo("Info");
            notificationManager.notify(1, notificationBuilder.build());
        }
    }

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {

            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    @SuppressLint("NewApi")
    private void sendNotification1(RemoteMessage remoteMessage) {
        if (!isAppIsInBackground(getApplicationContext())) {
            //foreground app
            Log.e("remoteMessage", remoteMessage.getData().toString());
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String timestamp = "";
            getTimeMilliSec(timestamp);
//            String type = remoteMessage.getData().get("type");
//            String to_id = remoteMessage.getData().get("to_id");
//            String from_id = remoteMessage.getData().get("from_id");

//            Log.e("title", title);
//            Log.e("body", type);
//            Log.e("type", type);
//            Log.e("to_id", to_id);
//            Log.e("from_id", from_id);
            Intent resultIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
            resultIntent.putExtra("key", remoteMessage.getData().get("type"));
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            OreoNotification oreoNotification = new OreoNotification(this);
            Notification.Builder builder = oreoNotification.getOreoNotification(title, body, timestamp, pendingIntent, defaultsound, String.valueOf(R.drawable.follow_connect_logo));

            int i = 0;
            oreoNotification.getManager().notify(i, builder.build());
        } else {
            Log.e("remoteMessage", remoteMessage.getData().toString());
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            String type = remoteMessage.getData().get("type");
            String to_id = remoteMessage.getData().get("to_id");
            String from_id = remoteMessage.getData().get("from_id");

            Log.e("title", title);
            Log.e("body", type);
            Log.e("type", type);
            Log.e("to_id", to_id);
            Log.e("from_id", from_id);
            String timestamp = "";
            getTimeMilliSec(timestamp);
            Intent resultIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
            //   resultIntent.putExtra("Notification","Notification");

            resultIntent.putExtra("key", remoteMessage.getData().get("type"));
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            OreoNotification oreoNotification = new OreoNotification(this);
            Notification.Builder builder = oreoNotification.getOreoNotification(title, body, timestamp, pendingIntent, defaultsound, String.valueOf(R.drawable.follow_connect_logo));

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int i = 0;
            oreoNotification.getManager().notify(i, builder.build());
        }

    }

    public static long getTimeMilliSec(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(timeStamp);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
