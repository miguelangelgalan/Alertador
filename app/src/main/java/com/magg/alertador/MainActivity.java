 package com.magg.alertador;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.magg.alertador.helper.Notificator;
import com.magg.alertador.service.SearchService;
import com.magg.alertador.service.SearchServiceT;

 public class MainActivity extends AppCompatActivity {
     private EditText statusText;
     private Bundle resultados = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        statusText = (EditText) findViewById(R.id.statusText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Add broadcast by code
        IntentFilter filtro = new IntentFilter(ReceptorBusqueda.ACTION_RESP);
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(new ReceptorBusqueda(),filtro);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

     public void buttonStartOn(View view) {

         if (startService(new Intent(this, SearchServiceT.class)) != null) {
             statusText.setText("ARRANCADO!");
         }
         //SearchService ss = new SearchService();
         //ss.setStatus("ARRANCADO");
     }

     public void buttonStopOn(View view) {

         if (stopService(new Intent(this, SearchServiceT.class))) {
             statusText.setText("PARADO");
         }
     }

     public void buttonResultsOn(View view) {

         Intent i = new Intent(this, ResultsActivity.class);
         i.putExtras(resultados);
         this.startActivity(i);


     }

     // To check receive responses

     public class ReceptorBusqueda extends BroadcastReceiver {
         public static final String ACTION_RESP="com.magg.alertador.action.RESPUESTA_BUSQUEDA";

         @Override

         public void onReceive(Context context, Intent intent) {
             // Double res = intent.getDoubleExtra("resultado",0.0);
             // salida.append(" " + res);
             resultados = intent.getExtras();
             int totalResults = intent.getIntExtra("TotalResults", 0);
             statusText.setText(" RESULTADO RECIBIDO: " + totalResults);

             // No hace falta, ¡¡Lo estoy viendo!!
            // Notificator.generateNotification(0,context,ResultsActivity.class, "AlertadorT:", "Busqueda Finalizada", intent);

         }
     }



}
