package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.AttrNameValue;

/**
 * Created by purushoy on 4/23/2015.
 */
public class SpecialDarshanAdapter extends BaseAdapter {

    private Context mContext;
    private AttrNameValue[] calendarArray = new AttrNameValue[0];

    public SpecialDarshanAdapter(Context c) {
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

        View gridRow;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridRow = inflater.inflate(R.layout.special_darshan_row, null);
        } else {
            gridRow = convertView;
        }

        Button dateButton = (Button) gridRow.findViewById(R.id.dateButton);
        Button nineButton = (Button) gridRow.findViewById(R.id.nineAMButton);
        Button tenAMButton = (Button) gridRow.findViewById(R.id.tenAMButton);
        Button elevenAMButton = (Button) gridRow.findViewById(R.id.elevenAMButton);
        Button twelvAMButton = (Button) gridRow.findViewById(R.id.twelvAMButton);
        Button onePMButton = (Button) gridRow.findViewById(R.id.onePMButton);
        Button twoPMButton = (Button) gridRow.findViewById(R.id.twoPMButton);
        Button threePMButton = (Button) gridRow.findViewById(R.id.threePMButton);
        Button fourPMButton = (Button) gridRow.findViewById(R.id.fourPMButton);
        Button fivePMButton = (Button) gridRow.findViewById(R.id.fivePMButton);
        Button sixPMButton = (Button) gridRow.findViewById(R.id.sixPMButton);
        Button sevenPMButton = (Button) gridRow.findViewById(R.id.sevenPMButton);
        AttrNameValue attrNameValue = calendarArray[position];
        if (attrNameValue.getMonth() != null) {
            dateButton.setText(attrNameValue.getMonth() + "-" + attrNameValue.getValue());
        } else {
            dateButton.setText("");
        }
        dateButton.setBackgroundResource(R.drawable.gray_circle);

        /*dayButton.setText(attrNameValue.getValue());
        dayButton.setBackgroundResource(R.drawable.gray_circle);*/

        nineButton.setText(attrNameValue.getAvailability().get("t9").getValue());
        setButtonBackground(nineButton, attrNameValue.getAvailability().get("t9").getName());

        tenAMButton.setText(attrNameValue.getAvailability().get("t10").getValue());
        setButtonBackground(tenAMButton, attrNameValue.getAvailability().get("t10").getName());

        elevenAMButton.setText(attrNameValue.getAvailability().get("t11").getValue());
        setButtonBackground(elevenAMButton, attrNameValue.getAvailability().get("t11").getName());

        twelvAMButton.setText(attrNameValue.getAvailability().get("t12").getValue());
        setButtonBackground(twelvAMButton, attrNameValue.getAvailability().get("t12").getName());

        onePMButton.setText(attrNameValue.getAvailability().get("t1").getValue());
        setButtonBackground(onePMButton, attrNameValue.getAvailability().get("t1").getName());

        twoPMButton.setText(attrNameValue.getAvailability().get("t2").getValue());
        setButtonBackground(twoPMButton, attrNameValue.getAvailability().get("t2").getName());

        threePMButton.setText(attrNameValue.getAvailability().get("t3").getValue());
        setButtonBackground(threePMButton, attrNameValue.getAvailability().get("t3").getName());

        fourPMButton.setText(attrNameValue.getAvailability().get("t4").getValue());
        setButtonBackground(fourPMButton, attrNameValue.getAvailability().get("t4").getName());

        fivePMButton.setText(attrNameValue.getAvailability().get("t5").getValue());
        setButtonBackground(fivePMButton, attrNameValue.getAvailability().get("t5").getName());

        sixPMButton.setText(attrNameValue.getAvailability().get("t6").getValue());
        setButtonBackground(sixPMButton, attrNameValue.getAvailability().get("t6").getName());

        sevenPMButton.setText(attrNameValue.getAvailability().get("t7").getValue());
        setButtonBackground(sevenPMButton, attrNameValue.getAvailability().get("t7").getName());
        return gridRow;
    }

    private void setButtonBackground(Button button, final String css) {
        if (css.startsWith("g")) {
            button.setBackgroundResource(R.drawable.green_circle);
        } else if (css.startsWith("b")) {
            button.setBackgroundResource(R.drawable.blue_circle);
        } else if (css.startsWith("r")) {
            button.setBackgroundResource(R.drawable.red_circle);
        } else {
            button.setBackgroundResource(R.drawable.gray_circle);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (css.startsWith("g") || css.startsWith("r") || css.startsWith("b")) {
                    AttrNameValue attr = new AttrNameValue(css, null);
                }
            }
        });
    }
}
