package com.npu_app.npu_application.model;


public class Week {
    private int weekNo;
    private String activity;
    private String handout;
    private boolean expandable;
    private String link;

    public Week(String activity, String handout, int weekNo, String link) {
        this.activity = activity;
        this.handout = handout;
        this.weekNo = weekNo;
        this.expandable = false;
        this.link = link;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getHandout() {
        return handout;
    }

    public void setHandout(String handout) {
        this.handout = handout;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
    public String getWeekNo() {
        return "week " + Integer.toString(weekNo);
    }
    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    @Override
    public String toString() {
        return "Week{" +
                "weekNo='" + weekNo + '\'' +
                ", activity='" + activity + '\'' +
                ", handout='" + handout + '\'' +
                ", expandable=" + expandable +
                '}';
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

