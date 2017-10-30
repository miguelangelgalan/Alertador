package com.magg.alertador.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by 6836076 on 19/10/2017.
 */
public class SearchEngine {
    private ArrayList<ISearch> searchers;
    private ArrayList <SearchResult> searchResults;

    private ArrayList<ISearch> getSearchers() {
        if (searchers == null) {
            searchers = new ArrayList<ISearch>();
        }
        return searchers;
    }

    private ArrayList<SearchResult> getSearchResults() {
        if (searchResults == null) {
            searchResults = new ArrayList<SearchResult>();
        }
        return searchResults;
    }

    public ArrayList<SearchResult> doSearch() {
        for (ISearch element : getSearchers()) {
            SearchResult searchResult = element.search();
            getSearchResults().add((SearchResult) searchResult);
        }

        return getSearchResults();
    }

    public void addSearcher(ISearch searcher) {
        getSearchers().add(searcher);
    }
}
