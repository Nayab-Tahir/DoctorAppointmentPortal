package com.nayab.doctorappointmentportal.myClasses;

public class DoctorForm {
    private String email;
    private String degree1;
    private String institution1;
    private String degree2;
    private String institution2;
    private int experience;
    private int availability;

    public DoctorForm() {
        this.email = "";
        this.degree1 = "";
        this.institution1 = "";
        this.degree2 = "";
        this.institution2 = "";
        this.experience = 0;
        this.availability = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree1() {
        return degree1;
    }

    public void setDegree1(String degree1) {
        this.degree1 = degree1;
    }

    public String getInstitution1() {
        return institution1;
    }

    public void setInstitution1(String institution1) {
        this.institution1 = institution1;
    }

    public String getDegree2() {
        return degree2;
    }

    public void setDegree2(String degree2) {
        this.degree2 = degree2;
    }

    public String getInstitution2() {
        return institution2;
    }

    public void setInstitution2(String institution2) {
        this.institution2 = institution2;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
}
