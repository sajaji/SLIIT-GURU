package com.example.sliitguru;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MemoBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        Intent repeating_Intent = new Intent(context,MainActivity.class);
        repeating_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle bundle= intent.getBundleExtra("Details");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification), 128, 128, false))
                .setContentTitle(bundle.getString("Title"))
                .setContentText(bundle.getString("Desc"))
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());
    }
}

