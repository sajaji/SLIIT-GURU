package com.example.sliitguru;

public class reminderDataModel {
    private int id;
    private String detail;
    private String title; //hospital
    private String date;
    private String time;

    public reminderDataModel(int id, String title, String detail, String date, String time) {
        this.id = id;
        this.detail = detail;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
