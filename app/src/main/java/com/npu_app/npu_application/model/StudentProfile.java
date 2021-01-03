package com.npu_app.npu_application.model;

import org.chalup.microorm.annotations.Column;

public class StudentProfile {
    @Column("student_id")
    private String student_id;
    @Column("first_name")
    private String first_name;
    @Column("last_name")
    private String last_name;
    @Column("program")
    private String program;
    @Column("gender")
    private String gender;
    @Column("citizenship")
    private String citizenship;
    @Column("martial_status")
    private String martial_status;
    @Column("student_contact")
    private String student_contact;
    @Column("student_email")
    private String student_email;
    @Column("student_address")
    private String student_address;
    @Column("emergency_contact_name")
    private String emergency_contact_name;
    @Column("emergency_contact_number")
    private String emergency_contact_number;
    @Column("emergency_contact_email")
    private String emergency_contact_email;
    @Column("student_type")
    private String student_type;
    @Column("visa_status")
    private String visa_status;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMartial_status() {
        return martial_status;
    }

    public void setMartial_status(String martial_status) {
        this.martial_status = martial_status;
    }

    public String getStudent_contact() {
        return student_contact;
    }

    public void setStudent_contact(String student_contact) {
        this.student_contact = student_contact;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_address() {
        return student_address;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public String getEmergency_contact_number() {
        return emergency_contact_number;
    }

    public void setEmergency_contact_number(String emergency_contact_number) {
        this.emergency_contact_number = emergency_contact_number;
    }

    public String getEmergency_contact_email() {
        return emergency_contact_email;
    }

    public void setEmergency_contact_email(String emergency_contact_email) {
        this.emergency_contact_email = emergency_contact_email;
    }

    public String getStudent_type() {
        return student_type;
    }

    public void setStudent_type(String student_type) {
        this.student_type = student_type;
    }

    public String getVisa_status() {
        return visa_status;
    }

    public void setVisa_status(String visa_status) {
        this.visa_status = visa_status;
    }
}
