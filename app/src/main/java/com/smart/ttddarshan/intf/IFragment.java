package com.smart.ttddarshan.intf;

import android.content.Context;

/**
 * Created by Purushotham on 06-05-2015.
 */
public interface IFragment {
    public Context getContext();

    public void loadingCalendarData();

    public void loadedCalendarData();
}
