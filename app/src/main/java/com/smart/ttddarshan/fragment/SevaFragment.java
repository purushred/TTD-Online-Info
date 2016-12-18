package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.SevaAdapter;
import com.smart.ttddarshan.vo.SevaVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purushoy on 11/21/2016.
 */

public class SevaFragment extends Fragment {

    private SevaAdapter mAdapter;
    private List<SevaVO> sevaList = new ArrayList<>();
    private Context context;
    private static Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seva, null);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new SevaAdapter(sevaList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        final AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mAdView.bringToFront();
            }
        });

        prepareSevaData();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static Fragment getInstance() {
        if (fragment == null) {
            fragment = new SevaFragment();
        }
        return fragment;
    }

    public void prepareSevaData() {
        SevaVO sevaVO = new SevaVO("Suprabatham", "Desc1", "Suprabatam.jpg", 3);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Nijapada Darshanam", "Desc1", "nijapada_darshan.jpg", 4);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Thomala Seva", "Desc1", "thomala.jpg", 5);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Archana", "Desc1", "archana.jpg", 6);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Visesha Pooja", "Desc1", "visesha_puja.jpg", 7);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Astadala Pada Padmaradhanamu", "Desc1", "astadala_aadapadmaradana.jpg", 8);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Kalyanotsavam", "Desc1", "kalyanotsavam.jpg", 12);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Vasanthotsavam", "Desc1", "vasanthotsavam.jpg", 13);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Unjal Seva", "Desc1", "unjalseva.jpeg", 14);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Sahasra Deepalankara Seva", "Desc1", "sahasra_deepalankarana.jpg", 15);
        sevaList.add(sevaVO);

        sevaVO = new SevaVO("Arjitha Brahmotsavam", "Desc1", "arjitha_brahmotsavam.jpg", 16);
        sevaList.add(sevaVO);

        mAdapter.notifyDataSetChanged();
    }
}
