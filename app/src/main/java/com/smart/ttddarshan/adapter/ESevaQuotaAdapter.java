package com.smart.ttddarshan.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.vo.QuotaVO;

import java.util.ArrayList;

/**
 * Created by purushoy on 5/7/2015.
 */
public class ESevaQuotaAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<QuotaVO> quotaVOList;

    public ESevaQuotaAdapter(Context c) {
        mContext = c;
        populateQuota();
    }

    private void populateQuota() {

        quotaVOList = new ArrayList<>();
        QuotaVO quotaVO = new QuotaVO("Seva Name", "Current Boking", "Online Booking",
                "E-Darshan Booking", "Performed Days", "Cost(Rs.)", "Persons Allowed", "Return Gift");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Archana", "Nil", "10", "Nil", "Tue, Wed & Thu", "220", "1", "2 Small laddus");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Thomala", "Nil", "10", "Nil", "Tue, Wed & Thu", "220", "1", "2 Small laddus");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Astadala Pada Padmaradhana", "Nil", "20", "Nil", "Tuesday only", "1,250", "1", "Two Big Laddus," +
                "Two Vadas," + "One Art silk Upper (or) One Bonus Piece");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Vishesha Pooja", "125", "250", "125", "Monday Only", "600", "1", "One Big Laddu," +
                "One Vada" + "One Art Silk Upper (or) One Blouse Piece");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Nijapada Darshanam", "100", "300", "200", "Friday Only", "1,250", "1", "Two Big Laddus," +
                "Two Vadas," + "One Art silk Upper (or) One Bonus Piece");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Suprabatham", "100", "50", "50", "Every Day", "120", "1", "2 Small laddus");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Kalyanotsavam", "100", "300", "100", "Every Day", "1000", "2", "Two Big laddus Two vadas Five Small laddus One Art silk Upper (or) One Blouse Piece");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Vasanthostavam", "250", "180", "120", "Every Day", "300", "1", "Two Small Laddus");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Brahmotsavam", "125", "90", "60", "Every Day", "200", "1", "Two Small Laddu");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Deepalankara", "350", "125", "125", "Every Day", "200", "1", "Two Small Laddus");
        quotaVOList.add(quotaVO);
        quotaVO = new QuotaVO("Unjal", "50", "50", "50", "Every Day", "200", "1", "Two Small Laddus");
        quotaVOList.add(quotaVO);
    }

    public int getCount() {
        return quotaVOList.size();
    }

    public QuotaVO getItem(int position) {
        return quotaVOList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            gridView = inflater.inflate(R.layout.activity_quota_row, null);

        } else {
            gridView = convertView;
        }
        QuotaVO quotaVO = getItem(position);
        TextView sevaName = (TextView) gridView.findViewById(R.id.sevaName);
        sevaName.setText(quotaVO.getSevaName());
        TextView quota = (TextView) gridView.findViewById(R.id.quota);
        quota.setText(quotaVO.getInternetBooking());
        TextView performedDays = (TextView) gridView.findViewById(R.id.performedDays);
        performedDays.setText(quotaVO.getPerformedDays());
        if (position == 0) {
            sevaName.setTypeface(null, Typeface.BOLD);
            quota.setTypeface(null, Typeface.BOLD);
            performedDays.setTypeface(null, Typeface.BOLD);
        } else {

            sevaName.setTypeface(null, Typeface.NORMAL);
            quota.setTypeface(null, Typeface.NORMAL);
            performedDays.setTypeface(null, Typeface.NORMAL);
        }
        return gridView;
    }
}
