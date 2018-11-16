package com.technology.waangyukui.elema;

/**
 * Created by lenvo on 2018/5/24.
 */

public class Type {
    private int status;
    private String type;

    public Type(int status, String type) {
        this.status = status;
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
