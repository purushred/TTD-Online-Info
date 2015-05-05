package com.smart.ttddarshan.vo;


import java.io.Serializable;
import java.util.List;

public class CalendarVO implements Serializable {
    public List<DayNameValuePair> calendar;
    public String viewState;
    public String eventValidation;

    public List<DayNameValuePair> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<DayNameValuePair> calendar) {
        this.calendar = calendar;
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
