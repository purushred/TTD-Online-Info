package com.smart.ttddarshan.vo;

/**
 * Created by Purushotham on 18-05-2015.
 */

public class ECounterVO {

    private String city;
    private String address;
    private String state;

    public ECounterVO(String city, String state, String address) {
        this.city = city;
        this.state = state;
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

