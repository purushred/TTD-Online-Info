package com.smart.ttddarshan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.smart.ttddarshan.adapter.CalendarAdapter;
import com.smart.ttddarshan.intf.ITaskComplete;
import com.smart.ttddarshan.vo.CalendarVO;
import com.smart.ttddarshan.vo.DateVO;
import com.smart.ttddarshan.vo.DayNameValuePair;
import com.smart.ttddarshan.vo.PostDataVO;

import java.util.ArrayList;

public class SevaFragment extends Fragment implements AdapterView.OnItemSelectedListener, ITaskComplete {

    public GridView gridview;
    public Spinner monthSpinner;
    public Spinner sevaSpinner;
    private InterstitialAd mInterstitialAd;
    private DateVO dateVO;
    private CalendarAdapter calendarAdapter;
    private LinearLayout progressBarLayout;
    private TextView selectedView;

    public SevaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_seva, container, false);
        selectedView = (TextView) rootView.findViewById(R.id.selectedView);
        mInterstitialAd = new InterstitialAd(this.getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.adUnitId));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                itemSelectHandler();
            }
        });

        gridview = (GridView) rootView.findViewById(R.id.gridview);
        calendarAdapter = new CalendarAdapter(this.getActivity());
        gridview.setAdapter(calendarAdapter);

        monthSpinner = (Spinner) rootView.findViewById(R.id.monthSpinner);
        sevaSpinner = (Spinner) rootView.findViewById(R.id.sevaSpinner);
        progressBarLayout = (LinearLayout) rootView.findViewById(R.id.progressBarLayout);
        Toast.makeText(this.getActivity(), "Please wait while loading seva information", Toast.LENGTH_LONG).show();
        new HTMLAsyncTask(this).execute();
        requestNewInterstitial();

        getActivity().setTitle("TTD Seva Availability");
        return rootView;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void itemSelectHandler() {
        String sevaType = AppUtils.sevaMap.get(sevaSpinner.getSelectedItem());
        String month = dateVO.getDatesMap().get(monthSpinner.getSelectedItem());

        selectedView.setText("Availability of '" + sevaSpinner.getSelectedItem() + "' in " + monthSpinner.getSelectedItem());
        PostDataVO dataVO = new PostDataVO(sevaType, month, dateVO.getViewState(),
                dateVO.getEventValidation());
        new HTTPPostAsyncTask(this, sevaType, month).execute(dataVO);
    }

    @Override
    public void onComplete(DateVO result) {
        this.dateVO = result;
        ArrayAdapter datesAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,
                new ArrayList<String>(result.getDatesMap().keySet()));
        datesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(datesAdapter);

        ArrayAdapter sevaAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item,
                new ArrayList<String>(AppUtils.sevaMap.keySet()));
        sevaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sevaSpinner.setAdapter(sevaAdapter);

        sevaSpinner.setOnItemSelectedListener(this);
        monthSpinner.setOnItemSelectedListener(this);
    }

    public void setCalendarData(CalendarVO calendarData) {
        calendarAdapter.setCalendarArray(calendarData.getCalendar().toArray(new DayNameValuePair[0]));
        calendarAdapter.notifyDataSetChanged();
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
        if (dateVO != null && dateVO.getDatesMap().size() > 0) {
            itemSelectHandler();
            if (mInterstitialAd.isLoaded()) {
                if (Math.random() >= 0.35) {
                    mInterstitialAd.show();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}