package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.ECounterAdapter;
import com.smart.ttddarshan.utils.AppUtils;

public class ECounterFragment extends Fragment {

    public GridView gridview;
    private ECounterAdapter counterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ecounter, container, false);
        AppUtils.initInterstitialAds(getActivity());
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        counterAdapter = new ECounterAdapter(getActivity());
        gridview.setAdapter(counterAdapter);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getActivity().setTitle("TTD E-Darshan Counters");
        return rootView;
    }


}