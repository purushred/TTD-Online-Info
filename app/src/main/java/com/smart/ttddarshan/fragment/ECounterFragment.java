package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.ECounterAdapter;

public class ECounterFragment extends Fragment {

    public GridView gridview;
    private InterstitialAd mInterstitialAd;
    private ECounterAdapter counterAdapter;

    public ECounterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ecounter, container, false);
        loadAd();

        gridview = (GridView) rootView.findViewById(R.id.gridview);
        counterAdapter = new ECounterAdapter(getActivity());
        gridview.setAdapter(counterAdapter);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getActivity().setTitle("TTD E-Darshan Counters");
        return rootView;
    }

    private void loadAd() {

        mInterstitialAd = new InterstitialAd(this.getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.adUnitId));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
}