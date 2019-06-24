package com.booksnippetshub.model;

import android.telephony.SignalStrength;

public class NotifationModel {
    public  int feedid;
    public  String fromavatarurl;
    public  String  fromnickname;
    public  int id;
    public  String  msg;

    public NotifationModel() {
    }

    public NotifationModel(int feedid, String fromavatarurl, String fromnickname, int id, String msg) {
        this.feedid = feedid;
        this.fromavatarurl = fromavatarurl;
        this.fromnickname = fromnickname;
        this.id = id;
        this.msg = msg;
    }

    public int getFeedid() {
        return feedid;
    }

    public void setFeedid(int feedid) {
        this.feedid = feedid;
    }

    public String getFromavatarurl() {
        return fromavatarurl;
    }

    public void setFromavatarurl(String fromavatarurl) {
        this.fromavatarurl = fromavatarurl;
    }

    public String getFromnickname() {
        return fromnickname;
    }

    public void setFromnickname(String fromnickname) {
        this.fromnickname = fromnickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
