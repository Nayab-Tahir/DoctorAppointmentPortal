package com.nayab.doctorappointmentportal.myClasses;
import java.sql.*;

public class DoctorFormDAO {
    Connection con;
    
    public DoctorFormDAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/docappointment1";
        con = DriverManager.getConnection(url, "root", "mysqldatabase");
    }
    
    public boolean insertDoctorForm(DoctorForm df) throws SQLException{
        PreparedStatement pst = con.prepareStatement("INSERT INTO doctor_form VALUES(?,?,?,?,?,?,?)");
        pst.setString(1, df.getEmail());
        pst.setString(2, df.getDegree1());
        pst.setString(3, df.getInstitution1());
        pst.setString(4, df.getDegree2());
        pst.setString(5, df.getInstitution2());
        pst.setInt(6, df.getExperience());
        pst.setInt(7, df.getAvailability());
        int check = pst.executeUpdate();
        
        if(check == 0)
            return false;
        else
            return true;
        
    }
    
    public int getDoctorNo(String d1) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM doctor_form WHERE degree1=?");
        pst.setString(1, d1);
        
        ResultSet rs = pst.executeQuery();
        rs.next();
        int number = rs.getInt(1);
        return number;
    }
    
    public int getDoctorNo(String d1, String d2) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM doctor_form WHERE degree1=? AND degree2=?");
        pst.setString(1, d1);
        pst.setString(2, d2);
        
        ResultSet rs = pst.executeQuery();
        rs.next();
        int number = rs.getInt(1);
        return number;
    }
    
    public int[] getTotalDoctorNumber()throws SQLException{
        int[] list = new int[10];
        
        list[0] = getDoctorNo("MBBS", "Dermatologist");
        list[1] = getDoctorNo("MBBS", "Urologist");
        list[2] = getDoctorNo("MBBS", "Child Specialist");
        list[3] = getDoctorNo("MBBS", "Gynecologist");
        list[4] = getDoctorNo("MBBS", "Heart Specialist");
        list[5] = getDoctorNo("MBBS", "Psychiatrist");
        list[6] = getDoctorNo("BDS");
        list[7] = getDoctorNo("MBBS", "Orthopedist");
        list[8] = getDoctorNo("MBBS", "Eye Specialist");
        list[9] = getDoctorNo("MBBS", "General Physician");
        
        return list;
    }
    
    public void deleteDoctorForm(String em) throws SQLException{
        PreparedStatement pst = con.prepareStatement("DELETE FROM doctor_form WHERE email=?");
        pst.setString(1, em);
        int isDeleted = pst.executeUpdate();
        
    }
    
    public void closeConnection() throws SQLException {
        if(con != null){
            con.close();
            con = null;
        }
    }
}
