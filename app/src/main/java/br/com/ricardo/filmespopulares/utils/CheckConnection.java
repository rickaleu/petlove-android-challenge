package br.com.ricardo.filmespopulares.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConnection {

    public static boolean verifyConnection(Context context){

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        boolean connected = netInfo != null && netInfo.isConnected();

        return connected;
    }

}
