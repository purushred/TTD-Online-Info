package com.smart.ttddarshan;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by purushoy on 3/30/2015.
 */
public class AppUtils {

    public static String SITE_URL = "https://www.ttdsevaonline.com/eSeva/AvailabilityChart.aspx";
    public static final String TTD_SEVA_URL = "http://apsrtc-reddy.rhcloud.com/";
    public static final Map<String,String> sevaMap = new HashMap<>(11);
    public static String STATE_STR;
    public static String VALIDATION_STR;

    static {
        sevaMap.put("Archana","6");
        sevaMap.put("Arjitha Brahmotsavam","16");
        sevaMap.put("Astadala Pada Padmaradhanamu","8");
        sevaMap.put("Kalyanotsavam","12");
        sevaMap.put("Nijapada Darshanam","4");
        sevaMap.put("Sahasra Deepalankara Seva","15");
        sevaMap.put("Suprabhatam","3");
        sevaMap.put("Thomala Seva","5");
        sevaMap.put("Unjal Seva","14");
        sevaMap.put("Vasanthotsavam","13");
        sevaMap.put("Visesha Pooja","7");
    }

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
