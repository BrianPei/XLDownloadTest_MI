package com.xunlei.download.utils.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table "FTP".
 */
public class FTP {

    private Integer ID;
    private String URL;

    public FTP() {
    }

    public FTP(Integer ID) {
        this.ID = ID;
    }

    public FTP(Integer ID, String URL) {
        this.ID = ID;
        this.URL = URL;
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

}
