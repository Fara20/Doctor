package com.example.farahreza.demo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class PushReceiver extends BroadcastReceiver {
    Session session;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("f",
                    "Farah's Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Farah's Channel");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "f")
                .setSmallIcon(R.drawable.clncc) // notification icon
                .setContentTitle("Do Not Miss Your Appointment") // title for notification
                .setContentText("You Have an appointment on 2 Days Time")// message for notification
                // set alarm sound for notification
                .setAutoCancel(true); // clear notification after click
        ////Intent i = new Intent(context, PatientServices.class);
        //PendingIntent pi = PendingIntent.getActivity(context, 0,i, PendingIntent.FLAG_UPDATE_CURRENT);
        // mBuilder.setContentIntent(pi);
        if(intent.getAction().equals("Farah"))
        {        mNotificationManager.notify(100, mBuilder.build());}
    }
}
