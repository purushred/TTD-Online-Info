package com.smart.ttddarshan.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.AttrNameValue;

/**
 * Created by Purushotham on 29-05-2015.
 */

public class TTDDialogFragment {

    public static void showDialog(final Context mContext, AttrNameValue attrNameValue) {
        final Dialog dialog = new Dialog(mContext);
        if (attrNameValue.getValue() != null)
            dialog.setTitle(attrNameValue.getValue() + "-" + TTDFragment.selectedMonth);
        else
            dialog.setTitle("Special Darshan Availability");

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout horizontalLayout = new LinearLayout(mContext);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(5, 5, 5, 5);

        TextView tv = new TextView(mContext);
        tv.setTextSize(18);
        tv.setPadding(5, 0, 4, 10);
        linearLayout.addView(tv);

        if (attrNameValue.getName().startsWith("g")) {
            tv.setText("Ticket available for booking online. Proceed to book on TTD Online.");
            Button b1 = new Button(mContext);
            b1.setText("Book Now");
            b1.setTextColor(mContext.getResources().getColor(R.color.white));
            b1.setBackgroundResource(R.drawable.blue_circle);
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String url = "https://www.ttdsevaonline.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    mContext.startActivity(i);
                    dialog.dismiss();
                }
            });
            horizontalLayout.addView(b1, layoutParams);
        } else if (attrNameValue.getName().startsWith("r")) {
            tv.setText("No tickets available for booking.");
        } else if (attrNameValue.getName().startsWith("b")) {
            tv.setText("Quota not yet released. Please check later.");
        }

        Button b2 = new Button(mContext);
        b2.setTextColor(mContext.getResources().getColor(R.color.white));
        b2.setText("Close");
        b2.setBackgroundResource(R.drawable.orange_circle);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        horizontalLayout.addView(b2, layoutParams);
        horizontalLayout.setHorizontalGravity(Gravity.CENTER);
        linearLayout.addView(horizontalLayout);
        linearLayout.setHorizontalGravity(Gravity.CENTER);
        dialog.setContentView(linearLayout);
        dialog.show();
    }
}