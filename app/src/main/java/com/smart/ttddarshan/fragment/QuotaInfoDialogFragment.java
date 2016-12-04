package com.smart.ttddarshan.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.QuotaVO;

/**
 * Created by Purushotham on 30-04-2015.
 */
public class QuotaInfoDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        QuotaVO quotaVO = (QuotaVO) getArguments().getSerializable("QuotaVO");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.quota_information_dialog, null);
        TextView currentBooking = (TextView) rootView.findViewById(R.id.currentBooking);
        TextView internetBooking = (TextView) rootView.findViewById(R.id.internetBooking);
        TextView eDarshanBooking = (TextView) rootView.findViewById(R.id.eDarshanBooking);
        TextView cost = (TextView) rootView.findViewById(R.id.cost);
        TextView personsAllowed = (TextView) rootView.findViewById(R.id.personsAllowed);
        TextView returnGift = (TextView) rootView.findViewById(R.id.returnGift);
        currentBooking.setText("Current Booking Quota: " + quotaVO.getCurrentBooking());
        internetBooking.setText("Online Booking Quota: " + quotaVO.getInternetBooking());
        eDarshanBooking.setText("E-Darshan Booking Quota: " + quotaVO.geteDarshanBooking());
        cost.setText("Cost: Rs." + quotaVO.getCost());
        personsAllowed.setText("Persons Allowed: " + quotaVO.getPersonsAllowed());
        returnGift.setText("Return Gift: " + quotaVO.getReturnGift());
        builder.setView(rootView)
                .setTitle(quotaVO.getSevaName())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}