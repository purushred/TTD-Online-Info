package com.smart.ttddarshan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.AccommodationDetailsVO;

import java.util.List;

/**
 * Created by purushoy on 11/20/2016.
 */

public class AccommodationSlotsAdapter extends RecyclerView.Adapter<AccommodationSlotsAdapter.MyViewHolder> {

    private List<AccommodationDetailsVO> darshanSlots;

    public AccommodationSlotsAdapter(List<AccommodationDetailsVO> darshanSlots) {
        this.darshanSlots = darshanSlots;
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
//        holder.maxPersonsView.setText("Max persons: " + darshanSlotVO.maxPersonsBooking);
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
        //        TextView maxPersonsView;
        TextView ticketPrice;
        TextView noOfTicketsView;
        TextView slotTimeView;
        TextView descriptionView;
        TextView locationView;

        MyViewHolder(View view) {
            super(view);
            slotTimeView = (TextView) view.findViewById(R.id.slotTimeView);
            noOfTicketsView = (TextView) view.findViewById(R.id.noOfTicketsView);
            ticketPrice = (TextView) view.findViewById(R.id.ticketPrice);
//            maxPersonsView = (TextView) view.findViewById(R.id.maxPersonsView);
            descriptionView = (TextView) view.findViewById(R.id.descriptionView);
            locationView = (TextView) view.findViewById(R.id.locationView);
        }
    }
}