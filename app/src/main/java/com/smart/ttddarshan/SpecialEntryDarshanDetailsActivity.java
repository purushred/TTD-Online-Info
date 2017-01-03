package com.smart.ttddarshan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.adapter.SpecialDarshanSlotsAdapter;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.DarshanDetailsVO;

public class SpecialEntryDarshanDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_entry_darshan_details);
        AppUtils.initInterstitialAds(this);
        DarshanDetailsVO darshanDetails = (DarshanDetailsVO) getIntent().getSerializableExtra("darshanDetails");
        if (darshanDetails.darshanSlots != null && darshanDetails.darshanSlots.size() > 0)
            getSupportActionBar().setTitle("Darshan Slots: " + darshanDetails.darshanSlots.get(0).darshanDate);
        SpecialDarshanSlotsAdapter mAdapter = new SpecialDarshanSlotsAdapter(darshanDetails.darshanSlots,this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mAdView.bringToFront();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
