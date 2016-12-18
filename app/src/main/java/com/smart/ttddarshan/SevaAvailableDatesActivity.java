package com.smart.ttddarshan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.adapter.SevaAvailableDatesAdapter;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;
import com.smart.ttddarshan.vo.SevaVO;

public class SevaAvailableDatesActivity extends AppCompatActivity {

    private static int adCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seva_available_dates);
        initInterstitialAds();
        SevaAvailabilityVO sevaAvail = (SevaAvailabilityVO) getIntent().getSerializableExtra("sevaAvail");
        SevaVO sevaVO = (SevaVO) getIntent().getSerializableExtra("sevaVO");
        getSupportActionBar().setTitle(sevaVO.getName() + " booking availability");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        TextView sevaInformationView = (TextView) findViewById(R.id.sevaInformationView);
        TextView noBookingsView = (TextView) findViewById(R.id.noBookingsView);
        if (!sevaAvail.frequency.toLowerCase().equals("daily")) {
            String days = "Seva is performed on : ";
            for (String day : sevaAvail.frequencyList) {
                days += day + ", ";
            }
            sevaInformationView.setText(days.substring(0, days.lastIndexOf(",")));
        } else {
            sevaInformationView.setText("Seva performed daily");
        }
        if (sevaAvail.availableDatesList == null || sevaAvail.availableDatesList.length == 0) {
            recyclerView.setVisibility(View.GONE);
            noBookingsView.setVisibility(View.VISIBLE);
            noBookingsView.setText("All slots are booked till: " + sevaAvail.startAndEndDates[1]);
            return;
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noBookingsView.setVisibility(View.GONE);
        }
        SevaAvailableDatesAdapter mAdapter = new SevaAvailableDatesAdapter(sevaAvail, sevaVO, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
