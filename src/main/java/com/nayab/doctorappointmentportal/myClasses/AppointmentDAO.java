package com.nayab.doctorappointmentportal.myClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDAO {
    Connection con;
    
    public AppointmentDAO() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/docappointment1";
        con = DriverManager.getConnection(url, "root", "mysqldatabase");
    }
    
    public boolean request(Appointment ap) throws SQLException{
        PreparedStatement pst = con.prepareStatement("INSERT INTO appointments VALUES(?,?,?,?,?)");
        pst.setString(1, ap.getDoctor());
        pst.setString(2, ap.getPatient());
        pst.setString(3, ap.getDay());
        pst.setString(4, ap.getTime());
        pst.setString(5, ap.getStatus());
        int check = pst.executeUpdate();
        if(check == 0)
            return false;
        else
            return true;
    }
    
    public boolean updateRequest(Appointment ap) throws SQLException{
        PreparedStatement pst = con.prepareStatement("UPDATE appointments SET status=? WHERE doctor=? AND patient=? AND day=? AND time=?");
        pst.setString(1, ap.getStatus());
        pst.setString(2, ap.getDoctor());
        pst.setString(3, ap.getPatient());
        pst.setString(4, ap.getDay());
        pst.setString(5, ap.getTime());
        int isUpdated = pst.executeUpdate();
        if(isUpdated == 0)
            return false;
        else
            return true;
    }
    
    public ArrayList<Appointment> search(String pat) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM appointments WHERE patient=?");
        pst.setString(1, pat);
        ResultSet rs = pst.executeQuery();
        ArrayList<Appointment> list = new ArrayList<>();
        while(rs.next()){
            Appointment ap = new Appointment();
            ap.setDoctor(rs.getString(1));
            ap.setPatient(rs.getString(2));
            ap.setDay(rs.getString(3));
            ap.setTime(rs.getString(4));
            ap.setStatus(rs.getString(5));
            list.add(ap);
        }
        return list;
    }
    
    public ArrayList<Appointment> search2(String doc) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM appointments WHERE doctor=?");
        pst.setString(1, doc);
        ResultSet rs = pst.executeQuery();
        ArrayList<Appointment> list = new ArrayList<>();
        while(rs.next()){
            Appointment ap = new Appointment();
            ap.setDoctor(rs.getString(1));
            ap.setPatient(rs.getString(2));
            ap.setDay(rs.getString(3));
            ap.setTime(rs.getString(4));
            ap.setStatus(rs.getString(5));
            list.add(ap);
        }
        return list;
    }
    
    public void delete(String email) throws SQLException{
        PreparedStatement pst = con.prepareStatement("DELETE FROM appointments WHERE doctor=? OR patient=?");
        pst.setString(1, email);
        pst.setString(2, email);
        pst.executeUpdate();
    }
    
    public void closeConnection() throws SQLException{
        if(con != null){
            con.close();
            con = null;
        }
    }
}
