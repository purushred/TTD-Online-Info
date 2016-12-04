package com.smart.ttddarshan.vo;

import java.io.Serializable;

/**
 * Created by Purushotham on 07-05-2015.
 */
public class QuotaVO implements Serializable {
    private String sevaName;
    private String internetBooking;
    private String currentBooking;
    private String eDarshanBooking;
    private String cost;
    private String personsAllowed;
    private String returnGift;
    private String performedDays;

    public QuotaVO(String sevaName, String currentBooking, String internetBooking, String eDarshanBooking, String performedDays, String cost, String personsAllowed, String returnGift) {
        this.sevaName = sevaName;
        this.currentBooking = currentBooking;
        this.internetBooking = internetBooking;
        this.eDarshanBooking = eDarshanBooking;
        this.performedDays = performedDays;
        this.cost = cost;
        this.personsAllowed = personsAllowed;
        this.returnGift = returnGift;
    }

    public String getSevaName() {
        return sevaName;
    }

    public void setSevaName(String sevaName) {
        this.sevaName = sevaName;
    }

    public String getInternetBooking() {
        return internetBooking;
    }

    public void setInternetBooking(String internetBooking) {
        this.internetBooking = internetBooking;
    }

    public String getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(String currentBooking) {
        this.currentBooking = currentBooking;
    }

    public String geteDarshanBooking() {
        return eDarshanBooking;
    }

    public void seteDarshanBooking(String eDarshanBooking) {
        this.eDarshanBooking = eDarshanBooking;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPersonsAllowed() {
        return personsAllowed;
    }

    public void setPersonsAllowed(String personsAllowed) {
        this.personsAllowed = personsAllowed;
    }

    public String getReturnGift() {
        return returnGift;
    }

    public void setReturnGift(String returnGift) {
        this.returnGift = returnGift;
    }

    public String getPerformedDays() {
        return performedDays;
    }

    public void setPerformedDays(String performedDays) {
        this.performedDays = performedDays;
    }
}