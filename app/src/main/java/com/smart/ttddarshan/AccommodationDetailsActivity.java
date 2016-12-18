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
import com.smart.ttddarshan.adapter.AccommodationSlotsAdapter;
import com.smart.ttddarshan.vo.AccomDetailsWrapper;
import com.smart.ttddarshan.vo.AccommodationDetailsVO;

import java.util.List;

public class AccommodationDetailsActivity extends AppCompatActivity {

    private static int adCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_entry_darshan_details);
        initInterstitialAds();
        AccomDetailsWrapper wrapper = (AccomDetailsWrapper) getIntent().getSerializableExtra("accomDetails");
        List<AccommodationDetailsVO> accomDetailsList = wrapper.accommodationDetailsVOList;
        if (accomDetailsList != null && accomDetailsList.size() > 0)
            getSupportActionBar().setTitle("Accommodation On " + accomDetailsList.get(0).accomDate);
        AccommodationSlotsAdapter mAdapter = new AccommodationSlotsAdapter(accomDetailsList,this);
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
