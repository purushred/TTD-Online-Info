package com.smart.ttddarshan;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.adapter.ESevaQuotaAdapter;
import com.smart.ttddarshan.fragment.QuotaInfoDialogFragment;


public class ESevaQuotaActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ESevaQuotaAdapter eSevaQuotaAdapter;
    private static int adCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInterstitialAds();
        setContentView(R.layout.activity_eseva_quota);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        eSevaQuotaAdapter = new ESevaQuotaAdapter(this);
        gridview.setAdapter(eSevaQuotaAdapter);
        gridview.setOnItemClickListener(this);

        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Log.e("Ads", "onAdLoaded");
                mAdView.bringToFront();
            }
        });
    }

    private void initInterstitialAds() {
        final InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interAdUnitId));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    if ((++adCount) % 3 == 0)
                        mInterstitialAd.show();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QuotaInfoDialogFragment fragment = new QuotaInfoDialogFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("QuotaVO", eSevaQuotaAdapter.getItem(position));
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "Quota Info");
    }
}
