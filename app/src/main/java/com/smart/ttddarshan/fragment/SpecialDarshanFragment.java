package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.SpecialDarshanAdapter;
import com.smart.ttddarshan.async.TTDAsyncTask;
import com.smart.ttddarshan.intf.IFragment;
import com.smart.ttddarshan.intf.ITaskComplete;
import com.smart.ttddarshan.vo.AttrNameValue;
import com.smart.ttddarshan.vo.TTDVO;

import java.util.List;

public class SpecialDarshanFragment extends Fragment implements ITaskComplete, IFragment {

    public GridView gridview;
    private InterstitialAd mInterstitialAd;
    private SpecialDarshanAdapter darshanAdapter;
    private LinearLayout progressBarLayout;

    public SpecialDarshanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_special_darshan, container, false);
        loadAd();

        gridview = (GridView) rootView.findViewById(R.id.gridview);
        darshanAdapter = new SpecialDarshanAdapter(getActivity());
        gridview.setAdapter(darshanAdapter);

        progressBarLayout = (LinearLayout) rootView.findViewById(R.id.progressBarLayout);
        new TTDAsyncTask(this, 3, "", "", "", "", "", "", "", "").execute();
        requestNewInterstitial();

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getActivity().setTitle("Special Darshan Availability");
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

    @Override
    public void onComplete(TTDVO result) {
        loadedCalendarData();
        if (result != null && result.getMonthsList() != null && result.getMonthsList().size() > 0) {
            List<AttrNameValue> monthsList = result.getMonthsList();
            darshanAdapter.setCalendarArray(monthsList.toArray(new AttrNameValue[0]));
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    public void loadingCalendarData() {
        progressBarLayout.setVisibility(View.VISIBLE);
        gridview.setVisibility(View.GONE);
    }

    public void loadedCalendarData() {
        progressBarLayout.setVisibility(View.GONE);
        gridview.setVisibility(View.VISIBLE);
    }
}