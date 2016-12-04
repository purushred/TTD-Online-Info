package com.smart.ttddarshan.async;

import android.os.AsyncTask;

import com.smart.ttddarshan.intf.ITaskComplete;
import com.smart.ttddarshan.restful.TTDService;
import com.smart.ttddarshan.utils.AppUtils;
import com.smart.ttddarshan.vo.TTDVO;

import retrofit.RestAdapter;

/**
 * Created by purushoy on 3/30/2015.
 */
public class TTDAsyncTask extends AsyncTask<Void, Void, TTDVO> {

    private final ITaskComplete taskComplete;
    private final int index;
    private String eventTarget;
    private String eventArg;
    private String viewStateGenerator;
    private String cmbDate;
    private String cmbSeva;
    private String lastFocus;
    private String eventValidation;
    private String viewState;

    public TTDAsyncTask(ITaskComplete taskComplete, int index, String eventTarget, String eventArg,
                        String viewStateGenerator, String cmbDate, String cmbSeva, String lastFocus,
                        String eventValidation, String viewState) {
        this.taskComplete = taskComplete;
        this.index = index;
        this.eventTarget = eventTarget;
        this.eventArg = eventArg;
        this.viewStateGenerator = viewStateGenerator;
        this.cmbDate = cmbDate;
        this.cmbSeva = cmbSeva;
        this.lastFocus = lastFocus;
        this.eventValidation = eventValidation;
        this.viewState = viewState;
    }

    @Override
    protected TTDVO doInBackground(Void... params) {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(AppUtils.TTD_SEVA_URL).build();
        TTDService sevaService = adapter.create(TTDService.class);
        return sevaService.getAvailabilityMonths(index, eventTarget, eventArg,
                viewStateGenerator, cmbDate, cmbSeva, lastFocus, eventValidation, viewState);
    }

    @Override
    protected void onPostExecute(TTDVO result) {
        taskComplete.onComplete(result);
    }
}