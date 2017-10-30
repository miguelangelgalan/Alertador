package com.magg.alertador.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.magg.alertador.MainActivity;
import com.magg.alertador.R;
import com.magg.alertador.helper.Notificator;

/**
 * Created by 6836076 on 17/10/2017.
 */
public class SearchService extends Service {
    private String status;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"Servicio Alertador Creado",Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Start ISearch
        //return super.onStartCommand(intent, flags, startId);

        Notificator.generateNotification(0,this,MainActivity.class, "Alertador:", "Servicio Iniciado");

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Stop Search
        //nm.cancel(ID_NOTIFICATION_CREATE);   // Dont use in this case, as we want the notification even if the service is off.
        Toast.makeText(this,"Servicio Alertador Finalizado",Toast.LENGTH_SHORT).show();
        Notificator.generateNotification(0,this,MainActivity.class, "Alertador:", "Servicio Finalizado");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    String prueba() {
        setStatus("running");
        return getStatus();
    }
}
