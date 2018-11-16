package com.technology.waangyukui.observer;

/**
 * Created by lenvo on 2018/6/7.
 */

public class MessageEventTwo {
    private String name;
    private String phone;
    public MessageEventTwo(String name, String phone){
        this.name=name;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
