package com.smart.ttddarshan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.R;

/**
 * Created by purushoy on 3/30/2015.
 */
public class AppUtils {

    public static final String TTD_SEVA_URL = "http://212.47.239.192:3000/";
    private static int adCount;

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

    public static void initInterstitialAds(Context context) {
        final InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.interAdUnitId));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    if ((++adCount) == 3) {
                        adCount = 0;
                        mInterstitialAd.show();
                    }
                }
            }
        });
    }
}
