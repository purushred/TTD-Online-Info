package com.smart.ttddarshan;

import android.os.AsyncTask;

import com.smart.ttddarshan.intf.ITaskComplete;
import com.smart.ttddarshan.restful.TTDeSevaService;
import com.smart.ttddarshan.vo.DateVO;

import retrofit.RestAdapter;

/**
 * Created by purushoy on 3/30/2015.
 */
public class HTMLAsyncTask extends AsyncTask<Void, Void, DateVO> {
    private final ITaskComplete taskComplete;

    public HTMLAsyncTask(ITaskComplete taskComplete) {
        this.taskComplete = taskComplete;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected DateVO doInBackground(Void... params) {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
        TTDeSevaService sevaService = adapter.create(TTDeSevaService.class);
        DateVO initialData = sevaService.getInitialData();
        return initialData;
    }

    @Override
    protected void onPostExecute(DateVO result) {
        taskComplete.onComplete(result);
    }
}