package com.magg.alertador.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.magg.alertador.service.SearchServiceT;

/**
 * Created by 6836076 on 18/10/2017.
 *
 * Starts SearchService when system starts
 */
public class SystemStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, SearchServiceT.class));
    }
}
