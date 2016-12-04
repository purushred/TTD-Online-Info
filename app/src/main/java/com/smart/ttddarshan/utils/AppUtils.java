package com.smart.ttddarshan.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by purushoy on 3/30/2015.
 */
public class AppUtils {

    public static final String TTD_SEVA_URL = "http://212.47.239.192:3000/";
    public static Map<String, String> sessionDetailsMap = new HashMap<>(2);
    public static String sessionId = "";
    public static String sessionToken = "";

    /**
     * To check whether internet is enabled.
     *
     * @param cm - Connectivity Manager class
     * @return true: network connected. false: network not connected.
     */
    public static boolean isNetworkOnline(ConnectivityManager cm) {
        boolean status = false;
        try {
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            Log.e("Network Error", e.getMessage());
            return false;
        }
        return status;
    }

}
