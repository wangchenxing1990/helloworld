package com.technology.waangyukui.observer;

/**
 * Created by lenvo on 2018/6/7.
 */

public class UserEvent {
    private String name;
    private String password;

    public  UserEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
