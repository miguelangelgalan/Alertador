package com.magg.alertador.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import com.magg.alertador.MainActivity;
import com.magg.alertador.ResultsActivity;
import com.magg.alertador.core.SearchEngine;
import com.magg.alertador.core.SearchResult;
import com.magg.alertador.helper.Configuracion;
import com.magg.alertador.helper.Notificator;
import com.magg.alertador.searches.EmilySearch;
import com.magg.alertador.searches.KarinaSearch;

import java.util.ArrayList;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SearchServiceT extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.magg.alertador.service.action.FOO";
    private static final String ACTION_BAZ = "com.magg.alertador.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.magg.alertador.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.magg.alertador.service.extra.PARAM2";

    public SearchServiceT() {
        super("SearchServiceT");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SearchServiceT.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SearchServiceT.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //Notificator.generateNotification(0,this,ResultsActivity.class, "AlertadorT:", "Servicio Iniciado");
        try {
            // Launch Search Engine
            SearchEngine searchEngine = new SearchEngine();
            EmilySearch eS = new EmilySearch();
            searchEngine.addSearcher(eS);

            KarinaSearch kS = new KarinaSearch();
            searchEngine.addSearcher(kS);


            ArrayList<SearchResult> searchResults = searchEngine.doSearch();

            // launch Broadcast message to return result
            Intent intentBroadcast = new Intent();
            intentBroadcast.setAction(MainActivity.ReceptorBusqueda.ACTION_RESP);
            intentBroadcast.addCategory(Intent.CATEGORY_DEFAULT);
            intentBroadcast.putExtra("TotalResults", searchResults.size());
            int i = 1;
            for (SearchResult sR : searchResults) {
                intentBroadcast.putExtra("#" + i + "Name", sR.getName());
                intentBroadcast.putExtra("#" + i + "Found", sR.isFound());
                intentBroadcast.putExtra("#" + i + "Result", sR.getResult());
                i++;
            }

            Intent intentNotification = new Intent(this, ResultsActivity.class);
            intentNotification.putExtra("TotalResults", searchResults.size());
            int j = 1;
            for (SearchResult sR : searchResults) {
                intentNotification.putExtra("#" + j + "Name", sR.getName());
                intentNotification.putExtra("#" + j + "Found", sR.isFound());
                intentNotification.putExtra("#" + j + "Result", sR.getResult());
                j++;
            }


            //sendBroadcast(intentBroadcast);
            //
            Notificator.generateNotification(0, this, ResultsActivity.class, "AlertadorT:", "Busqueda Finalizada", intentNotification);
            //--//
        } catch (Exception e) {
            Notificator.generateNotification(0, this, ResultsActivity.class, "AlertadorT:", "Busqueda FALLIDA");
        }

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }


        // I dont want this service running when task is done.
        stopSelf();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        //Notificator.generateNotification(0,this,MainActivity.class, "AlertadorT:", "Servicio Destruyendose");

        // I want to restart the service again in one hour
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000 * 60 * Configuracion.TIEMPO_EJECUCION_SERVICIO),
                PendingIntent.getService(this, 0, new Intent(this, SearchServiceT.class), 0)
        );
    }
}
