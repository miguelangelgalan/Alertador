package com.magg.alertador.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.magg.alertador.MainActivity;

/**
 * Created by 6836076 on 19/10/2017.
 */
public interface ISearch {
    public SearchResult search();
    public void registerSearch();

}
