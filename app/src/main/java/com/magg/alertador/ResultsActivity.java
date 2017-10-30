package com.magg.alertador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ResultsActivity extends AppCompatActivity {

    private int resultadoActual;
    public int totalResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        totalResults = getIntent().getIntExtra("TotalResults", 0);
        EditText totalResultstext = (EditText) findViewById(R.id.totalResultsTextId);

        if (null != totalResultstext) {
            totalResultstext.setText("" + totalResults);
        }

        showResult(1);
    }

    private void showResult(int i) {

        if (i>totalResults) i=totalResults;
        if (i<=0) i=1;

        EditText name = (EditText) findViewById(R.id.nameText);
        EditText found = (EditText) findViewById(R.id.statusText);
        EditText results = (EditText) findViewById(R.id.resultText);

        name.setText(getIntent().getStringExtra("#"+i+"Name"));
        found.setText(Boolean.toString(getIntent().getBooleanExtra("#"+i+"Found",false)));
        results.setText(getIntent().getStringExtra("#"+i+"Result"));

        resultadoActual = i;

//        intentBroadcast.putExtra("#"+i+"Name", sR.getName());
//        intentBroadcast.putExtra("#"+i+"Found", sR.isFound());
//        intentBroadcast.putExtra("#"+i+"Result", sR.getResult());    }
    }

    public void nextResukt(View view){
        showResult(--resultadoActual);
    }

    public void prevResult(View view) {
        showResult(++resultadoActual);
    }
}


