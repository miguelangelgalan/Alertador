package com.magg.alertador.searches;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.document.html.HtmlElement;
import com.gistlabs.mechanize.document.html.HtmlElements;
import com.gistlabs.mechanize.exceptions.MechanizeException;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.magg.alertador.core.ISearch;
import com.magg.alertador.core.SearchResult;
import com.magg.alertador.helper.HTTPCLIENT;

import java.util.List;

/**
 * Created by 6836076 on 19/10/2017.
 */
public class EmilySearch implements ISearch {
    private String baseUrl = "http://www.pasion.com/contactos/602447407.htm";
    @Override
    public SearchResult search() {

        // Do the Search
        SearchResult sR = new SearchResult();
        sR.setName("Emily");

        try {
            MechanizeAgent agent = HTTPCLIENT.getMechanizeAgent();
            HtmlDocument pg = agent.get(baseUrl);
            String pagina = pg.asString();
            int found = pagina.indexOf("(CADIZ)");
            //HtmlElements hes = pg.htmlElements();
            //List<HtmlElement> tds = hes.findAll("CADIZ");

            //if (tds.size() == 0) {   // YA NO CADIZ
            if (found == -1) {   // YA NO CADIZ
                sR.setFound(true);
                sR.setResult("Ya no está en (CADIZ)");
            } else {
                sR.setFound(false);
                sR.setResult("SIGUE en (CADIZ)");
            }
        } catch (MechanizeException e) {
            sR.setFound(false);
            sR.setResult("EXCEPCION en búsqueda");
        }

        return sR;
    }

    @Override
    public void registerSearch() {

    }



}
