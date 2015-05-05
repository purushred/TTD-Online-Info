package com.smart.ttddarshan;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.smart.ttddarshan.restful.TTDeSevaService;
import com.smart.ttddarshan.vo.CalendarVO;
import com.smart.ttddarshan.vo.PostDataVO;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by purushoy on 3/30/2015.
 */
public class HTTPPostAsyncTask extends AsyncTask<PostDataVO, Void, Void> {
    private final SevaFragment fragment;
    private final String month;
    private final String seva;
    private ProgressDialog pd;

    public HTTPPostAsyncTask(SevaFragment fragment, String seva, String month) {
        this.fragment = fragment;
        this.seva = seva;
        this.month = month;
    }

    @Override
    protected Void doInBackground(PostDataVO... params) {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
        TTDeSevaService stationService = adapter.create(TTDeSevaService.class);
        stationService.postData(params[0], new Callback<CalendarVO>() {
            @Override
            public void success(CalendarVO calendarVO, Response response) {

                if (calendarVO != null) {
                    fragment.setCalendarData(calendarVO);
                } else {
                    Toast.makeText(fragment.getActivity(), "Failed to retrieve calendar data.", Toast.LENGTH_SHORT).show();
                }
                fragment.loadedCalendarData();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(fragment.getActivity(), "Failed to retrieve calendar data.", Toast.LENGTH_SHORT).show();
                Log.e("Calendar data,", retrofitError.toString());
            }
        });
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragment.loadingCalendarData();
    }
}