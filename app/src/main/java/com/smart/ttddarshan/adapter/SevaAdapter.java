package com.smart.ttddarshan.adapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.ttddarshan.R;
import com.smart.ttddarshan.SevaAvailableDatesActivity;
import com.smart.ttddarshan.fragment.SevaFragment;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;
import com.smart.ttddarshan.vo.SevaVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by purushoy on 11/20/2016.
 */

public class SevaAdapter extends RecyclerView.Adapter<SevaAdapter.MyViewHolder> {

    private final SevaFragment fragment;
    private List<SevaVO> sevaList;

    public SevaAdapter(List<SevaVO> sevaList, SevaFragment fragment) {
        this.sevaList = sevaList;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seva_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SevaVO sevaVO = sevaList.get(position);
        holder.itemNameView.setText(sevaVO.getName());

        try {
            InputStream ims = fragment.getActivity().getAssets().open(sevaVO.getUrl());
            holder.sevaItemImageView.setImageDrawable(Drawable.createFromStream(ims, null));
            holder.sevaItemImageView.setTag(holder.sevaItemImageView.getId(), sevaVO);
        } catch (IOException e) {
            Log.e("Assets Image", e.toString());
        }

        holder.sevaItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AsyncTask<SevaVO, Void, SevaAvailabilityVO>() {
                    ProgressDialog pd = new ProgressDialog(fragment.getActivity());
                    SevaVO sevaVO;

                    @Override
                    protected void onPreExecute() {
                        pd.setMessage("Loading seva availability..");
                        pd.setIndeterminate(true);
                        pd.setCancelable(false);
                        pd.show();
                    }

                    @Override
                    protected SevaAvailabilityVO doInBackground(SevaVO... sevaVOs) {
                        sevaVO = sevaVOs[0];
                        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
                        TTDService sevaService = adapter.create(TTDService.class);
                        try {
                            return sevaService.getSevaAvailability(String.valueOf(sevaVO.getId()));
                        } catch (RetrofitError error) {
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(SevaAvailabilityVO sevaAvailabilityVO) {
                        pd.dismiss();
                        if (sevaAvailabilityVO == null) {
                            Toast.makeText(fragment.getActivity(), "Unable to get Seva availability.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(fragment.getActivity(), SevaAvailableDatesActivity.class);
                        intent.putExtra("sevaAvail", sevaAvailabilityVO);
                        intent.putExtra("sevaVO", sevaVO);
                        fragment.startActivity(intent);
                    }
                }.execute((SevaVO) view.getTag(view.getId()));
                AppUtils.initInterstitialAds(fragment.getActivity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sevaList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView;
        ImageView sevaItemImageView;
        RelativeLayout relativeLayout;

        MyViewHolder(View view) {
            super(view);
            itemNameView = (TextView) view.findViewById(R.id.itemNameView);
            sevaItemImageView = (ImageView) view.findViewById(R.id.sevaItemImageView);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        }
    }
}