package com.nayab.doctorappointmentportal.myClasses;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO {
    Connection con;
    
    public DoctorDAO() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/docappointment1";
        con = DriverManager.getConnection(url, "root", "mysqldatabase");
    }
    
    public ArrayList<Doctor> searchDoctor(String d1) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users, doctor_form WHERE users.email = doctor_form.email AND degree1=?");
        pst.setString(1, d1);
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Doctor> list = new ArrayList<Doctor>();
        while(rs.next()){
            Doctor doc = new Doctor();
            
            doc.setEmail(rs.getString(4));
            doc.setFirst_name(rs.getString(1));
            doc.setLast_name(rs.getString(2));
            doc.setGender(rs.getString(6));
            doc.setDegree1(rs.getString(10));
            doc.setInstitution1(rs.getString(11));
            doc.setDegree2(rs.getString(12));
            doc.setInstitution2(rs.getString(13));
            doc.setExperience(rs.getInt(14));
            doc.setAvailability(rs.getInt(15));
            
            list.add(doc);
        }
        
        return list;
    }
    
    
        public ArrayList<Doctor> searchDoctor(String d1, String d2) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users, doctor_form WHERE users.email = doctor_form.email AND degree1=? AND degree2=?");
        pst.setString(1, d1);
        pst.setString(2, d2);
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Doctor> list = new ArrayList<Doctor>();
        while(rs.next()){
            Doctor doc = new Doctor();
            
            doc.setEmail(rs.getString(4));
            doc.setFirst_name(rs.getString(1));
            doc.setLast_name(rs.getString(2));
            doc.setGender(rs.getString(6));
            doc.setDegree1(rs.getString(10));
            doc.setInstitution1(rs.getString(11));
            doc.setDegree2(rs.getString(12));
            doc.setInstitution2(rs.getString(13));
            doc.setExperience(rs.getInt(14));
            doc.setAvailability(rs.getInt(15));
            
            list.add(doc);
        }
        
        return list;
    }
        
    public ArrayList<Doctor> allDoctors() throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users, doctor_form WHERE users.email = doctor_form.email");
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Doctor> list = new ArrayList<Doctor>();
        while(rs.next()){
            Doctor doc = new Doctor();
            
            doc.setEmail(rs.getString(4));
            doc.setFirst_name(rs.getString(1));
            doc.setLast_name(rs.getString(2));
            doc.setGender(rs.getString(6));
            doc.setDegree1(rs.getString(10));
            doc.setInstitution1(rs.getString(11));
            doc.setDegree2(rs.getString(12));
            doc.setInstitution2(rs.getString(13));
            doc.setExperience(rs.getInt(14));
            doc.setAvailability(rs.getInt(15));
            
            list.add(doc);
        }
        
        return list;
    }
    
    public boolean deleteDoctor(Doctor d) throws ClassNotFoundException, SQLException{
        UserDAO u = new UserDAO();
        boolean check = u.deleteUser(d.getEmail());
        
        DoctorFormDAO df = new DoctorFormDAO();
        df.deleteDoctorForm(d.getEmail());
        
        return check;
    }
    
    public void closeConnection() throws SQLException{
        if(con != null){
            con.close();
            con = null;
        }
    }
    
}

