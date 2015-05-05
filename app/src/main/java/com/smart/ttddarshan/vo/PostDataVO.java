package com.smart.ttddarshan.vo;

public class PostDataVO {

    private String sevaType;
    private String month;
    private String viewState;
    private String eventValidation;

    public PostDataVO(){}

    public PostDataVO(String sevaType, String month, String viewState, String eventValidation) {
        this.sevaType = sevaType;
        this.month = month;
        this.viewState = viewState;
        this.eventValidation = eventValidation;
    }

    public String getSevaType() {
        return sevaType;
    }

    public void setSevaType(String sevaType) {
        this.sevaType = sevaType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
