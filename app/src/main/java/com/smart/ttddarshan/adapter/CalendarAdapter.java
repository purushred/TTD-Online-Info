package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.DayNameValuePair;

/**
 * Created by purushoy on 4/23/2015.
 */
public class CalendarAdapter extends BaseAdapter {

    private Context mContext;
    private DayNameValuePair[] calendarArray = new DayNameValuePair[0];

    public CalendarAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return calendarArray.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setCalendarArray(DayNameValuePair[] calendarArray) {
        this.calendarArray = calendarArray;
    }

    // create a new ImageView for each item referenced by the Adapter
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
        DayNameValuePair val = calendarArray[position];
        if (val != null) {
            if (val.getCssClass().equalsIgnoreCase("B")) {
                buttonView.setBackgroundResource(R.drawable.blue_circle);
            } else if (val.getCssClass().equalsIgnoreCase("R")) {
                buttonView.setBackgroundResource(R.drawable.red_circle);
            } else if (val.getCssClass().equalsIgnoreCase("G")) {
                buttonView.setBackgroundResource(R.drawable.green_circle);
            }
            else {
                buttonView.setBackgroundResource(R.drawable.gray_circle);
            }
            buttonView.setText(val.getDayStr());
        }
        return buttonView;
    }
}
