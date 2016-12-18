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
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.AccommodationAdapter;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by purushoy on 11/21/2016.
 */

public class AccommodationFragment extends Fragment {

    private AccommodationAdapter mAdapter;
    private Context context;
    private static Fragment fragment;
    private RecyclerView recyclerView;
    static int adCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accommodation, null);

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
                Log.e("Ads", "onAdLoaded");
                mAdView.bringToFront();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDarshanData(1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static Fragment getInstance() {
        if (fragment == null) {
            fragment = new AccommodationFragment();
        }
        return fragment;
    }

    public void getDarshanData(final int location) {

        final String locationStr;
        if (location == 0) return;
        if (location == 1) {
            locationStr = "Tirumala";
        } else {
            locationStr = "Tirupati";
        }
        new AsyncTask<Void, Void, SevaAvailabilityVO>() {

            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                try {
                    pd = new ProgressDialog(getActivity());
                    pd.setMessage("Searching accommodations in " + locationStr + "..");
                    pd.setIndeterminate(true);
                    pd.setCancelable(false);
                    pd.show();
                } catch (Exception ex) {
                    Log.e("Exception", ex.toString());
                }
            }

            @Override
            protected SevaAvailabilityVO doInBackground(Void... voids) {
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
                TTDService sevaService = adapter.create(TTDService.class);
                try {
                    return sevaService.getAccommodationAvailability(String.valueOf(location));
                } catch (RetrofitError error) {
                    Log.e("Retrofit Error", error.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(SevaAvailabilityVO specialDarshanVO) {
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
                if (specialDarshanVO == null || specialDarshanVO.availableDatesList.length == 0) {
                    Toast.makeText(getActivity(), "Unable to get accommodation details.", Toast.LENGTH_LONG).show();
                    return;
                }
                mAdapter = new AccommodationAdapter(specialDarshanVO, AccommodationFragment.this, location);
                recyclerView.setAdapter(mAdapter);
                initInterstitialAds();
            }
        }.execute();
    }

    private void initInterstitialAds() {
        final InterstitialAd mInterstitialAd = new InterstitialAd(getActivity());
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
}
