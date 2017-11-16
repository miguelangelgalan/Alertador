package com.magg.alertador.searches;

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
public class KarinaSearch implements ISearch {
    private String baseUrl = "http://www.pasion.com/contactos/636068697-karina.htm";

        public SearchResult search_() {
            SearchResult sR = new SearchResult();
            sR.setName("Karine");
            sR.setFound(true);
            sR.setResult("Test:" + System.currentTimeMillis());
        return sR;
        }

    @Override
        public SearchResult search() {

        // Do the Search
        SearchResult sR = new SearchResult();
        sR.setName("Karine");

        try {
            MechanizeAgent agent = HTTPCLIENT.getMechanizeAgent();
            HtmlDocument pg = agent.get(baseUrl);
            String pagina = pg.asString();
            int found = pagina.indexOf("KARINA");
            //HtmlElements hes = pg.htmlElements();
            //List<HtmlElement> tds = hes.findAll("CADIZ");


            if (found == -1) {   // NO ENCONTRADA
                sR.setFound(false);
                sR.setResult("NO SE ANUNCIA");
            } else {        // HAY ANUNCIO
                // Buscamos de hace cuantos días es
                HtmlElements hes = pg.htmlElements();
                List<HtmlElement> divs = hes.findAll("div[class]");
                for (HtmlElement e : divs) {
                    String attr = e.getAttribute("class");
                    if (attr.equalsIgnoreCase("x6")) {
                        String diasStr = e.getInnerHtml();
                        if (diasStr.contains("horas") || diasStr.contains("minutos")) { // Actualizado!!
                            sR.setFound(true);
                            sR.setResult("ACTUALIZADO HACE: " + diasStr);
                        } else if (diasStr.contains("Auto")){
                            sR.setFound(true);
                            sR.setResult("ACTUALIZADO: " + diasStr);
                        } else if (diasStr.contains("días")){
                        sR.setFound(false);
                        sR.setResult("ACTUALIZADO: " + diasStr);
                    }
                    }
                }
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
