package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.fragment.TTDDialogFragment;
import com.smart.ttddarshan.vo.AttrNameValue;

/**
 * Created by purushoy on 4/23/2015.
 */
public class TTDAdapter extends BaseAdapter {

    private Context mContext;
    private AttrNameValue[] calendarArray = new AttrNameValue[0];

    public TTDAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return calendarArray.length;
    }

    public AttrNameValue getItem(int position) {
        return calendarArray[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setCalendarArray(AttrNameValue[] calendarArray) {
        this.calendarArray = calendarArray;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Button buttonView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            buttonView = new Button(mContext);
            buttonView.setLayoutParams(new GridView.LayoutParams(80, 80));
            buttonView.setPadding(2, 2, 2, 2);

        } else {
            buttonView = (Button) convertView;
        }
        buttonView.setTextColor(Color.WHITE);
        final AttrNameValue val = calendarArray[position];
        if (val != null) {
            if (val.getName().equals("b")) {
                buttonView.setBackgroundResource(R.drawable.blue_circle);
            } else if (val.getName().equals("r")) {
                buttonView.setBackgroundResource(R.drawable.red_circle);
            } else if (val.getName().equals("g")) {
                buttonView.setBackgroundResource(R.drawable.green_circle);
            } else {
                buttonView.setBackgroundResource(R.drawable.gray_circle);
            }
            buttonView.setText(val.getValue());
            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (val.getName().equals("g") || val.getName().equals("r") || val.getName().equals("b"))
                        TTDDialogFragment.showDialog(mContext, val);
                }
            });
        }
        return buttonView;
    }

    private void showDialog(AttrNameValue val) {

    }
}
