package com.nayab.doctorappointmentportal.myClasses;

public class UserInfo {
    private String first_name;
    private String last_name;
    private String type;
    private String email;
    private String password;
    private String gender;
    private String birth_date;
    private boolean form;
    
    public UserInfo(){
        first_name = "";
        last_name = "";
        type = "";
        email = "";
        password = "";
        gender = "";
        birth_date = "";
        form = false;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setDate(String birth_date) {
        this.birth_date = birth_date;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }
    
    
}
