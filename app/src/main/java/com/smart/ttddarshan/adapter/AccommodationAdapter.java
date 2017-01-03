package com.smart.ttddarshan.adapter;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.ttddarshan.AccommodationDetailsActivity;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.AccomDetailsWrapper;
import com.smart.ttddarshan.vo.AccommodationDetailsVO;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by purushoy on 11/20/2016.
 */

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.MyViewHolder> {

    private final Fragment fragment;
    private final int locationId;
    private SevaAvailabilityVO specialDarshanVO;

    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat newFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy");

    public AccommodationAdapter(SevaAvailabilityVO specialDarshanVO, Fragment fragment, int locationId) {
        this.specialDarshanVO = specialDarshanVO;
        this.fragment = fragment;
        this.locationId = locationId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accommodation_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String darshanDateStr = specialDarshanVO.availableDatesList[position];
        try {
            Date date = format.parse(darshanDateStr);
            holder.itemNameView.setText(newFormat.format(date));
        } catch (ParseException e) {
            holder.itemNameView.setText(darshanDateStr);
            Log.e("Parse Error", e.toString());
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AsyncTask<Void, Void, List<AccommodationDetailsVO>>() {
                    ProgressDialog pd = new ProgressDialog(fragment.getActivity());

                    @Override
                    protected void onPreExecute() {
                        pd.setMessage("Loading accommodation details..");
                        pd.setIndeterminate(true);
                        pd.setCancelable(false);
                        pd.show();
                    }

                    @Override
                    protected List<AccommodationDetailsVO> doInBackground(Void... sevaVOs) {
                        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
                        TTDService sevaService = adapter.create(TTDService.class);
                        try {
                            return sevaService.getAccommodationDetails(darshanDateStr, String.valueOf(locationId));
                        } catch (RetrofitError error) {
                            Log.e("Retrofit Error", error.toString());
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(List<AccommodationDetailsVO> darshanSlotVOList) {
                        pd.dismiss();
                        if (darshanSlotVOList == null) {
                            Toast.makeText(fragment.getActivity(), "Unable to get accommodation details.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(fragment.getActivity(), AccommodationDetailsActivity.class);
                        AccomDetailsWrapper wrapper = new AccomDetailsWrapper();
                        wrapper.accommodationDetailsVOList = darshanSlotVOList;
                        intent.putExtra("accomDetails", wrapper);
                        fragment.startActivity(intent);
                    }
                }.execute();
                AppUtils.initInterstitialAds(fragment.getActivity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialDarshanVO.availableDatesList.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView;
        RelativeLayout relativeLayout;

        MyViewHolder(View view) {
            super(view);
            itemNameView = (TextView) view.findViewById(R.id.itemNameView);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        }
    }
}