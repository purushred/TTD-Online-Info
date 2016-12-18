package com.smart.ttddarshan.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.SpecialEntryDarshanDetailsActivity;
import com.smart.ttddarshan.vo.DarshanSlotVO;

import java.util.List;

/**
 * Created by purushoy on 11/20/2016.
 */

public class SpecialDarshanSlotsAdapter extends RecyclerView.Adapter<SpecialDarshanSlotsAdapter.MyViewHolder> {

    private final SpecialEntryDarshanDetailsActivity activity;
    private List<DarshanSlotVO> darshanSlots;

    public SpecialDarshanSlotsAdapter(List<DarshanSlotVO> darshanSlots,
                                      SpecialEntryDarshanDetailsActivity activity) {
        this.darshanSlots = darshanSlots;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.special_entry_darshan_slots_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DarshanSlotVO darshanSlotVO = darshanSlots.get(position);
        holder.slotTimeView.setText("Time: " + darshanSlotVO.slotName);
        holder.maxPersonsView.setText("Max persons: " + darshanSlotVO.maxNoOfPersons);
        holder.noOfTicketsView.setText("Tickets Available: " + darshanSlotVO.noOfTicketsAvailable);
        holder.ticketPrice.setText("Price: Rs." + darshanSlotVO.darshanPrice + "/person");
    }

    @Override
    public int getItemCount() {
        return darshanSlots.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView maxPersonsView;
        TextView ticketPrice;
        TextView noOfTicketsView;
        TextView slotTimeView;
        RelativeLayout relativeLayout;

        MyViewHolder(View view) {
            super(view);
            slotTimeView = (TextView) view.findViewById(R.id.slotTimeView);
            noOfTicketsView = (TextView) view.findViewById(R.id.noOfTicketsView);
            ticketPrice = (TextView) view.findViewById(R.id.ticketPrice);
            maxPersonsView = (TextView) view.findViewById(R.id.maxPersonsView);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://ttdsevaonline.com/#/sedAvailability";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    activity.startActivity(i);
                }
            });
        }
    }
}