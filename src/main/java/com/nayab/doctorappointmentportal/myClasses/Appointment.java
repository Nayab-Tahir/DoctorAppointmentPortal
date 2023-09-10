package com.nayab.doctorappointmentportal.myClasses;

public class Appointment {
    private String doctor;
    private String patient;
    private String day;
    private String time;
    private String status;
    
    public Appointment(){
        doctor = "";
        patient = "";
        time = "";
        status = "";
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
