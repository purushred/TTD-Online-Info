package com.smart.ttddarshan.vo;

import java.util.List;

/**
 * Created by purushoy on 4/23/2015.
 */
public class TTDVO {

    private List<AttrNameValue> monthsList;
    private List<AttrNameValue> sevaList;
    private List<AttrNameValue> calendar;
    private String viewState;
    private String eventValidation;
    private String eventArgument;
    private String eventTarget;
    private String lastFocus;
    private String viewStateGenerator;

    public List<AttrNameValue> getMonthsList() {
        return monthsList;
    }

    public void setMonthsList(List<AttrNameValue> monthsList) {
        this.monthsList = monthsList;
    }

    public List<AttrNameValue> getSevaList() {
        return sevaList;
    }

    public void setSevaList(List<AttrNameValue> sevaList) {
        this.sevaList = sevaList;
    }

    public List<AttrNameValue> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<AttrNameValue> calendar) {
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

    public String getEventArgument() {
        return eventArgument;
    }

    public void setEventArgument(String eventArgument) {
        this.eventArgument = eventArgument;
    }

    public String getEventTarget() {
        return eventTarget;
    }

    public void setEventTarget(String eventTarget) {
        this.eventTarget = eventTarget;
    }

    public String getLastFocus() {
        return lastFocus;
    }

    public void setLastFocus(String lastFocus) {
        this.lastFocus = lastFocus;
    }

    public String getViewStateGenerator() {
        return viewStateGenerator;
    }

    public void setViewStateGenerator(String viewStateGenerator) {
        this.viewStateGenerator = viewStateGenerator;
    }
}
