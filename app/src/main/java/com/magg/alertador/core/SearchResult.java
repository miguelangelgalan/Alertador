package com.magg.alertador.core;

/**
 * Created by 6836076 on 19/10/2017.
 *
 * Search Result Object
 */
public class SearchResult {
    private String name;
    private boolean found; // true/false
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isFound() {
        return found;
    }

}
