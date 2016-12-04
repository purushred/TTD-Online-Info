package com.smart.ttddarshan.vo;

import java.io.Serializable;

/**
 * Created by purushoy on 11/26/2016.
 */

public class SevaVO implements Serializable{
    private String name;
    private String desc;
    private String url;
    private int id;

    public SevaVO(String name, String desc, String url, int id) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
