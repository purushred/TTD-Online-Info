package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smart.ttddarshan.R;

/**
 * Created by Purushotham on 30-05-2015.
 */
public class CustomDrawerAdapter extends ArrayAdapter<String> {

    private int selectedItem;

    public CustomDrawerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    public void selectItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        TextView item = ((TextView) convertView);
        if (position == selectedItem) {
            item.setTypeface(null, Typeface.BOLD);
            item.setTextColor(getContext().getResources().getColor(R.color.white));
            item.setBackgroundResource(R.drawable.blue_rect);
        } else {
            item.setTypeface(null, Typeface.NORMAL);
            item.setTextColor(getContext().getResources().getColor(R.color.black));
            item.setBackgroundResource(R.drawable.white_rect);
        }

        return convertView;
    }
}