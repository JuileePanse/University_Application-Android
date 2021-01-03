package com.npu_app.npu_application.model;

import org.chalup.microorm.annotations.Column;

public class Events {
    @Column("event_id")
    String event_id;
    @Column("event_name")
    String event_name;
    @Column("event_time")
    String event_time;
    @Column("event_date")
    String event_date;
    @Column("event_month")
    String event_month;
    @Column("event_year")
    String event_year;
    @Column("event_completed/not")
    String event_completed_not;
    @Column("event_description")
    String event_description;

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_completed_not() {
        return event_completed_not;
    }

    public void setEvent_completed_not(String event_completed_not) {
        this.event_completed_not = event_completed_not;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }
    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_month() {
        return event_month;
    }

    public void setEvent_month(String event_month) {
        this.event_month = event_month;
    }

    public String getEvent_year() {
        return event_year;
    }

    public void setEvent_year(String event_year) {
        this.event_year = event_year;
    }

    @Override
    public String toString() {
        return "Events{" +
                "Event='" + event_id + '\'' +
                ", Time='" + event_time + '\'' +
                ", Date='" + event_date + '\'' +
                ", Month='" + event_month + '\'' +
                ", Year='" + event_year + '\'' +
                '}';
    }
}