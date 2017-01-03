package com.smart.ttddarshan.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.ttddarshan.AccommodationDetailsActivity;
import com.smart.ttddarshan.R;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.AccommodationDetailsVO;

import java.util.List;

/**
 * Created by purushoy on 11/20/2016.
 */

public class AccommodationSlotsAdapter extends RecyclerView.Adapter<AccommodationSlotsAdapter.MyViewHolder> {

    private final AccommodationDetailsActivity activity;
    private List<AccommodationDetailsVO> darshanSlots;

    public AccommodationSlotsAdapter(List<AccommodationDetailsVO> darshanSlots, AccommodationDetailsActivity activity) {
        this.darshanSlots = darshanSlots;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accommodation_slots_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AccommodationDetailsVO darshanSlotVO = darshanSlots.get(position);
        holder.slotTimeView.setText("Date: " + darshanSlotVO.accomDate);
        holder.noOfTicketsView.setText("Available Rooms: " + darshanSlotVO.noOfAccomAvailable);
        holder.ticketPrice.setText("Price: Rs." + darshanSlotVO.accomType);
        holder.descriptionView.setText("Description: " + darshanSlotVO.accomDesc);
        holder.locationView.setText("Location: " + darshanSlotVO.locationName);

    }

    @Override
    public int getItemCount() {
        return darshanSlots.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ticketPrice;
        TextView noOfTicketsView;
        TextView slotTimeView;
        TextView descriptionView;
        TextView locationView;
        RelativeLayout relativeLayout;

        MyViewHolder(View view) {
            super(view);
            slotTimeView = (TextView) view.findViewById(R.id.slotTimeView);
            noOfTicketsView = (TextView) view.findViewById(R.id.noOfTicketsView);
            ticketPrice = (TextView) view.findViewById(R.id.ticketPrice);
            descriptionView = (TextView) view.findViewById(R.id.descriptionView);
            locationView = (TextView) view.findViewById(R.id.locationView);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtils.initInterstitialAds(activity);
                    String url = "https://ttdsevaonline.com/#/accommodationCal";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    activity.startActivity(i);
                }
            });
        }
    }
}