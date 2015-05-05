package com.smart.ttddarshan.vo;

import java.util.Map;

/**
 * Created by purushoy on 4/23/2015.
 */
public class DateVO {
    private Map<String,String> datesMap;
    private String viewState;
    private String eventValidation;

    public Map<String, String> getDatesMap() {
        return datesMap;
    }

    public void setDatesMap(Map<String, String> datesMap) {
        this.datesMap = datesMap;
    }

    public String getViewState() {
        return viewState;
    }

    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    public String getEventValidation() {
        return eventValidation;
    }

    public void setEventValidation(String eventValidation) {
        this.eventValidation = eventValidation;
    }
}
