package com.npu_app.npu_application.model;

public class ClassSchedule {
    private String courseName;
    private String instructorName;
    private String courseCredit;
    private String time;
    private String meetingLink;
    private String activity;
    private String handout;
    private boolean expandable;

    public ClassSchedule(String courseName, String instructorName, String courseCredit, String time, String meetingLink, String activity, String handout) {
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.courseCredit = courseCredit;
        this.time = time;
        this.meetingLink = meetingLink;
        this.activity = activity;
        this.handout = handout;
        this.expandable = false;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = Integer.toString(courseCredit);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
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

    @Override
    public String toString() {
        return "ClassSchedule{" +
                "courseName='" + courseName + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", courseCredit=" + courseCredit +
                ", time='" + time + '\'' +
                ", meetingLink='" + meetingLink + '\'' +
                ", activity='" + activity + '\'' +
                ", handout='" + handout + '\'' +
                '}';
    }
}