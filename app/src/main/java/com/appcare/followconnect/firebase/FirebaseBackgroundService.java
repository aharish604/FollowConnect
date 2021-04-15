package com.appcare.followconnect.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.appcare.followconnect.R;
import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import static android.content.Context.NOTIFICATION_SERVICE;

public class FirebaseBackgroundService extends WakefulBroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("BroadcastReceiver::", "BroadcastReceiver");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.follow_connect_logo)
                .setContentTitle(intent.getExtras().getString("title"))
                .setContentText(intent.getExtras().getString("message"));
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
    
}