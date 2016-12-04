package com.smart.ttddarshan;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smart.ttddarshan.adapter.ESevaQuotaAdapter;
import com.smart.ttddarshan.fragment.QuotaInfoDialogFragment;


public class ESevaQuotaActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ESevaQuotaAdapter eSevaQuotaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_quota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QuotaInfoDialogFragment fragment = new QuotaInfoDialogFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("QuotaVO",eSevaQuotaAdapter.getItem(position));
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "Quota Info");
    }
}
