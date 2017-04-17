package com.example.cuileikun.androidandjava.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 2017/4/13.
 */

public class Model {


    /**
     * bold : 1
     * content : 商户存根
     * content-type : txt
     * italic : 0
     * position : center
     * size : 3
     */

    private String bold;
    private String content;
    @SerializedName("content-type")
    private String contenttype;
    private String italic;
    private String position;
    private String size;

    public String getBold() {
        return bold;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getItalic() {
        return italic;
    }

    public void setItalic(String italic) {
        this.italic = italic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
