package com.magg.alertador.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.magg.alertador.MainActivity;
import com.magg.alertador.R;
import android.app.Service;

/**
 * Created by 6836076 on 18/10/2017.
 */
public class Notificator {
    private static int ID_NOTIFICATION_CREATE = 1;
    private static NotificationManager nm = null;

    private static NotificationManager getNm(Context mContext) {
        if (nm == null) {
            nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return nm;
    }

    private static void setNm(NotificationManager nm) {
        Notificator.nm = nm;
    }



    public Notificator() {
    }

    public static void generateNotification(int TYPE, Context mContext, Class <?> clsActivity, String title, String text) {
        generateNotification(TYPE, mContext, clsActivity, title, text, null);
    }

    public static void generateNotification(int TYPE, Context mContext, Class <?> clsActivity, String title, String text, Intent intent) {

        // Para arrancar otra actividad al pulsar la notificación:
        // Intent i = new Intent(MainActivity.this, MensajeActivity.class);
        // PendingIntent pendingIntent = PendingIntent.getActivity (MainActivity.this, 0, i, 0);
        PendingIntent pendingIntent;
        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        if (null==intent) {
            pendingIntent = PendingIntent.getActivity (mContext, iUniqueId, new Intent(mContext, clsActivity), 0);
        } else {
            pendingIntent = PendingIntent.getActivity (mContext, iUniqueId, intent, 0);
        }


        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(mContext.getApplicationContext())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(text)
                .setVibrate(new long[] {100,250,100,250})
                .setAutoCancel(true);

        getNm(mContext).notify(ID_NOTIFICATION_CREATE, nBuilder.build());
        ID_NOTIFICATION_CREATE++;
    }
}
