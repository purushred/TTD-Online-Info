package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.SpecialEntryDarshanAdapter;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by purushoy on 11/21/2016.
 */

public class SpecialEntryDarshanFragment extends Fragment {

    private SpecialEntryDarshanAdapter mAdapter;
    private Context context;
    private static Fragment fragment;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_special_entry_darshan, null);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mAdView.bringToFront();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDarshanData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static Fragment getInstance() {
        if (fragment == null) {
            fragment = new SpecialEntryDarshanFragment();
        }
        return fragment;
    }

    public void getDarshanData() {

        new AsyncTask<Void, Void, SevaAvailabilityVO>() {

            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Loading special darshan availability details..");
                pd.setIndeterminate(true);
                pd.setCancelable(false);
                pd.show();
            }

            @Override
            protected SevaAvailabilityVO doInBackground(Void... voids) {
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
                TTDService sevaService = adapter.create(TTDService.class);
                try {
                    return sevaService.getDarshanAvailability();
                } catch (RetrofitError error) {
                    Log.e("Retrofit Error", error.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(SevaAvailabilityVO specialDarshanVO) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                if (specialDarshanVO == null || specialDarshanVO.availableDatesList.length == 0) {
                    Toast.makeText(getActivity(), "Unable to get Special Darshan details.", Toast.LENGTH_LONG).show();
                    return;
                }
                mAdapter = new SpecialEntryDarshanAdapter(specialDarshanVO, SpecialEntryDarshanFragment.this);
                recyclerView.setAdapter(mAdapter);
            }
        }.execute();
        AppUtils.initInterstitialAds(getActivity());
    }
}
