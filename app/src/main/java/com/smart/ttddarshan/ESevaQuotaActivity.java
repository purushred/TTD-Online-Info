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
import com.smart.ttddarshan.adapter.ESevaQuotaAdapter;
import com.smart.ttddarshan.fragment.QuotaInfoDialogFragment;
import com.smart.ttddarshan.utils.AppUtils;


public class ESevaQuotaActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ESevaQuotaAdapter eSevaQuotaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.initInterstitialAds(this);
        setContentView(R.layout.activity_eseva_quota);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QuotaInfoDialogFragment fragment = new QuotaInfoDialogFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("QuotaVO", eSevaQuotaAdapter.getItem(position));
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "Quota Info");
    }
}
