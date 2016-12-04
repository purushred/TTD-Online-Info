package com.smart.ttddarshan.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.adapter.TTDAdapter;
import com.smart.ttddarshan.async.TTDAsyncTask;
import com.smart.ttddarshan.intf.IFragment;
import com.smart.ttddarshan.intf.ITaskComplete;
import com.smart.ttddarshan.vo.AttrNameValue;
import com.smart.ttddarshan.vo.TTDVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TTDFragment extends Fragment implements AdapterView.OnItemSelectedListener, IFragment, ITaskComplete {

    public static String selectedMonth;
    public GridView gridview;
    public Spinner monthSpinner;
    public Spinner sevaSpinner;
    private InterstitialAd mInterstitialAd;
    private TTDAdapter ttdAdapter;
    private LinearLayout progressBarLayout;
    private int addCount = 0;
    private int index = 0;
    private List<AttrNameValue> monthsList;
    private LinearLayout linearLayout;
    private List<AttrNameValue> sevaList;
    private List<AttrNameValue> calendarData;
    private String eventArgument = "";
    private String eventTarget = "";
    private String eventValidation = "";
    private String lastFocus = "";
    private String viewState = "";
    private String viewStateGenerator = "";
    private int reqCount = 0;

    public TTDFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        View rootView = inflater.inflate(R.layout.fragment_ttd, container, false);
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        ttdAdapter = new TTDAdapter(getActivity());
        gridview.setAdapter(ttdAdapter);

        monthSpinner = (Spinner) rootView.findViewById(R.id.monthSpinner);
        sevaSpinner = (Spinner) rootView.findViewById(R.id.sevaSpinner);
        progressBarLayout = (LinearLayout) rootView.findViewById(R.id.progressBarLayout);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
        getActivity().setTitle(getFragmentTitle());
        new TTDAsyncTask(this, index, eventTarget, eventArgument, viewStateGenerator,
                "", "", lastFocus, eventValidation, viewState).execute();
        loadingCalendarData();
        loadAd();
        requestNewInterstitial();

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return rootView;
    }

    private void loadAd() {

        mInterstitialAd = new InterstitialAd(this.getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.adUnitId));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                itemSelectHandler();
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void itemSelectHandler() {
        if (reqCount > 1) {
            String month = monthsList.get(monthSpinner.getSelectedItemPosition()).getValue();
            String seva = sevaList.get(sevaSpinner.getSelectedItemPosition()).getValue();
            loadingCalendarData();
            new TTDAsyncTask(this, index, eventTarget, eventArgument, viewStateGenerator,
                    month, seva, lastFocus, eventValidation, viewState).execute();
        } else {
            reqCount++;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (monthsList.size() > 0) {
            itemSelectHandler();
            if (mInterstitialAd.isLoaded()) {
                if (addCount >= 3) {
                    mInterstitialAd.show();
                    addCount = 0;
                } else {
                    addCount++;
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public String getFragmentTitle() {
        String[] ttdServices = getResources().getStringArray(R.array.services_array);
        return ttdServices[index];
    }

    @Override
    public void onComplete(TTDVO result) {
        if (result == null || result.getMonthsList() == null) {
            Toast.makeText(getActivity(), "Request failed, Please try again.", Toast.LENGTH_LONG).show();
            return;
        }
        calendarData = result.getCalendar();
        calendarData.addAll(0, getHeader());
        monthsList = result.getMonthsList();
        sevaList = result.getSevaList();
        setCalendarData();
        loadedCalendarData();
        if (sevaSpinner.getAdapter() == null)
            setSpinnerData();
        setQueryParams(result);
        linearLayout.setVisibility(View.VISIBLE);
        selectedMonth = monthsList.get(monthSpinner.getSelectedItemPosition()).getName();
    }

    private Collection<? extends AttrNameValue> getHeader() {
        List<AttrNameValue> list = new ArrayList<>();
        AttrNameValue attrNameValue = new AttrNameValue("", "Sun");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Mon");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Tue");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Wed");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Thu");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Fri");
        list.add(attrNameValue);
        attrNameValue = new AttrNameValue("", "Sat");
        list.add(attrNameValue);
        return list;

    }

    private void setSpinnerData() {
        List<String> monthList = new ArrayList<>();
        for (AttrNameValue attr : monthsList) {
            monthList.add(attr.getName());
        }
        List<String> sevList = new ArrayList<>();
        for (AttrNameValue attr1 : sevaList) {
            sevList.add(attr1.getName());
        }
        ArrayAdapter datesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,
                monthList);
        datesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(datesAdapter);
        ArrayAdapter sevaAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,
                sevList);
        sevaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sevaSpinner.setAdapter(sevaAdapter);
        sevaSpinner.setOnItemSelectedListener(this);
        monthSpinner.setOnItemSelectedListener(this);

    }

    private void setQueryParams(TTDVO queryParams) {
        eventArgument = queryParams.getEventArgument();
        eventTarget = queryParams.getEventTarget();
        eventValidation = queryParams.getEventValidation();
        lastFocus = queryParams.getLastFocus();
        viewState = queryParams.getViewState();
        viewStateGenerator = queryParams.getViewStateGenerator();
    }

    private void setCalendarData() {
        ttdAdapter.setCalendarArray(calendarData.toArray(new AttrNameValue[0]));
        ttdAdapter.notifyDataSetChanged();
    }
}