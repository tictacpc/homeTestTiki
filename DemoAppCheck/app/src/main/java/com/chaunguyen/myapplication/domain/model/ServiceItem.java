package com.chaunguyen.myapplication.domain.model;

/**
 * Created on 2019-06-05.
 */
public class ServiceItem {

    int imgID;
    String content;

    public ServiceItem(int imgID, String content) {
        this.imgID = imgID;
        this.content = content;
    }

    public int getImgID() {
        return imgID;
    }

    public String getContent() {
        return content;
    }
}

