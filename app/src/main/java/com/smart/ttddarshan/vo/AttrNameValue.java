package com.smart.ttddarshan.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by Purushotham on 18-05-2015.
 */

public class AttrNameValue {

    private String name;
    private String value;
    private String month;
    private Map<String,AttrNameValue> availability;

    public AttrNameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Map<String, AttrNameValue> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, AttrNameValue> availability) {
        this.availability = availability;
    }
}

