package com.smart.ttddarshan.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.SevaDetailsActivity;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;
import com.smart.ttddarshan.vo.SevaDetailsVO;
import com.smart.ttddarshan.vo.SevaVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by purushoy on 11/20/2016.
 */

public class SevaAvailableDatesAdapter extends RecyclerView.Adapter<SevaAvailableDatesAdapter.MyViewHolder> {

    private SevaVO sevaVO;
    private final Activity activity;
    private SevaAvailabilityVO sevaAvailabilityVO;
    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat newFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy");

    public SevaAvailableDatesAdapter(SevaAvailabilityVO sevaAvailabilityVO, SevaVO sevaVO, Activity activity) {
        this.sevaAvailabilityVO = sevaAvailabilityVO;
        this.sevaVO = sevaVO;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seva_available_dates_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String dateStr = sevaAvailabilityVO.availableDatesList[position];
        try {
            Date date = format.parse(dateStr);
            holder.itemNameView.setText(newFormat.format(date));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final TextView itemView = (TextView) view.findViewById(R.id.itemNameView);
                    new AsyncTask<Void, Void, List<SevaDetailsVO>>() {
                        ProgressDialog pd = new ProgressDialog(activity);
                        String dateStr;

                        @Override
                        protected void onPreExecute() {
                            dateStr = itemView.getText().toString();
                            try {
                                dateStr = format.format(newFormat.parse(dateStr));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            pd.setMessage("Loading seva details..");
                            pd.setIndeterminate(true);
                            pd.setCancelable(false);
                            pd.show();
                        }

                        @Override
                        protected List<SevaDetailsVO> doInBackground(Void... voids) {
                            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
                            TTDService sevaService = adapter.create(TTDService.class);
                            try {
                                return sevaService.getSevaDetails(String.valueOf(sevaVO.getId()), dateStr);
                            } catch (RetrofitError error) {
                                Log.e("Retrofit Error:", error.toString());
                                return null;
                            }
                        }

                        @Override
                        protected void onPostExecute(List<SevaDetailsVO> sevaDetailsVOList) {
                            pd.dismiss();
                            if (sevaDetailsVOList == null || sevaDetailsVOList.size() == 0) {
                                Toast.makeText(activity, "Unable to get Seva details.", Toast.LENGTH_LONG).show();
                                return;
                            }
                            Intent intent = new Intent(activity, SevaDetailsActivity.class);
                            intent.putExtra("sevaDetails", sevaDetailsVOList.get(0));
                            activity.startActivity(intent);
                        }
                    }.execute();
                }
            });
        } catch (ParseException e) {
            Log.e("Date Parse:", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return sevaAvailabilityVO.availableDatesList.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView;
        CardView cardView;

        MyViewHolder(View view) {
            super(view);
            itemNameView = (TextView) view.findViewById(R.id.itemNameView);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }
}