package com.npu_app.npu_application.model;

public class GradeDetails {
    private String courseName;
    private String courseCredit;
    private String gpa;
    private String grade;

    public GradeDetails(String courseName, String courseCredit, String gpa, String grade) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.gpa = gpa;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GradeDetails{" +
                "courseName='" + courseName + '\'' +
                ", courseCredit='" + courseCredit + '\'' +
                ", gpa='" + gpa + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
