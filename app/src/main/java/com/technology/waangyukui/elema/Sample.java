package com.technology.waangyukui.elema;

/**
 * Created by lenvo on 2018/5/24.
 */

public class Sample {
    private String id;
    private String groupId;
    private String title;
    private String desc;
    private String groupTitle;
    private int count;

    public Sample(String id,String groupId,String title,String desc,String groupTitle,int count){
        this.id=id;
        this.groupId=groupId;
        this.title=title;
        this.desc=desc;
        this.groupTitle=groupTitle;
        this.count=count;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
