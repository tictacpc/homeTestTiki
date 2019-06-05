package com.chaunguyen.myapplication.domain.model;

/**
 * Created on 2019-06-05.
 */
public class HotKeyItemDTO {

    int colorId;
    String content;

    public HotKeyItemDTO(int colorId, String content) {
        this.colorId = colorId;
        this.content = content;
    }

    public int getColorId() {
        return colorId;
    }

    public String getContent() {
        return content;
    }
}

