package com.xunlei.download.utils.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "AD".
 */
public class AD {

    private Integer ID;
    private String URL;
    private String APKNAME;

    public AD() {
    }

    public AD(Integer ID) {
        this.ID = ID;
    }

    public AD(Integer ID, String URL, String APKNAME) {
        this.ID = ID;
        this.URL = URL;
        this.APKNAME = APKNAME;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAPKNAME() {
        return APKNAME;
    }

    public void setAPKNAME(String APKNAME) {
        this.APKNAME = APKNAME;
    }

}
