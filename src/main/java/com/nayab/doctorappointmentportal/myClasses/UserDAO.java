package com.nayab.doctorappointmentportal.myClasses;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    Connection con;
    
    public UserDAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/docappointment1";
        con = DriverManager.getConnection(url, "root", "mysqldatabase");
    }
    
    public UserInfo searchUser(String em, String ps) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE email=? and password=?;");
        pst.setString(1, em);
        pst.setString(2, ps);
        ResultSet rs = pst.executeQuery();
        
        UserInfo u = null;
        if(rs.next()){
            u = new UserInfo();
            u.setFirst_name(rs.getString(1));
            u.setLast_name(rs.getString(2));
            u.setType(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPassword(rs.getString(5));
            u.setGender(rs.getString(6));
            u.setDate(rs.getString(7));
            u.setForm(rs.getBoolean(8));
        }
        
        return u;
    }
    
    public UserInfo searchUser(String em) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE email = ?");
        pst.setString(1, em);
        ResultSet rs = pst.executeQuery();
        
        UserInfo u = null;
        if(rs.next()){
            u = new UserInfo();
            u.setFirst_name(rs.getString(1));
            u.setLast_name(rs.getString(2));
            u.setType(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPassword(rs.getString(5));
            u.setGender(rs.getString(6));
            u.setDate(rs.getString(7));
            u.setForm(rs.getBoolean(8));
        }
        
        return u;
    }
    
    
    
    public boolean insertUser(UserInfo u) throws SQLException{
        PreparedStatement pst = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?)");
        pst.setString(1, u.getFirst_name());
        pst.setString(2, u.getLast_name());
        pst.setString(3, u.getType());
        pst.setString(4, u.getEmail());
        pst.setString(5, u.getPassword());
        pst.setString(6, u.getGender());
        pst.setString(7, u.getBirth_date());
        pst.setBoolean(8, u.isForm());
        int check = pst.executeUpdate();
        
        if(check == 0)
            return false;
        else
            return true;
    }
    
    public boolean userExists(String em) throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE email = ?");
        pst.setString(1, em);
        ResultSet rs = pst.executeQuery();
        
        if(rs.next())
            return true;
        else
            return false;
    }
    
    public boolean updateUser_form(boolean f, String em) throws SQLException{
        PreparedStatement pst = con.prepareStatement("UPDATE users SET form = ? WHERE email = ?");
        pst.setBoolean(1, f);
        pst.setString(2, em);
        int check = pst.executeUpdate();
        
        if(check == 0)
            return false;
        else
            return true;
    }
    
    public ArrayList<UserInfo> allPatients() throws SQLException{
        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE type=?");
        pst.setString(1, "patient");
        ResultSet rs  = pst.executeQuery();
        
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        UserInfo u = null;
        while(rs.next()){
            u = new UserInfo();
            u.setFirst_name(rs.getString(1));
            u.setLast_name(rs.getString(2));
            u.setType(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPassword(rs.getString(5));
            u.setGender(rs.getString(6));
            u.setDate(rs.getString(7));
            u.setForm(rs.getBoolean(8));
            
            list.add(u);
        }
        
        return list;
    }
    
//    public ArrayList<UserInfo> allUsers() throws SQLException{
//        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE type=? OR type=?");
//        pst.setString(1, "patient");
//        pst.setString(2, "doctor");
//        ResultSet rs  = pst.executeQuery();
//        
//        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
//        UserInfo u = null;
//        while(rs.next()){
//            u = new UserInfo();
//            u.setFirst_name(rs.getString(1));
//            u.setLast_name(rs.getString(2));
//            u.setType(rs.getString(3));
//            u.setEmail(rs.getString(4));
//            u.setPassword(rs.getString(5));
//            u.setGender(rs.getString(6));
//            u.setDate(rs.getString(7));
//            u.setForm(rs.getBoolean(8));
//            
//            list.add(u);
//        }
//        
//        return list;
//    }
    
    public boolean deleteUser(String em) throws SQLException, ClassNotFoundException{
        
        PreparedStatement pst = con.prepareStatement("DELETE FROM users WHERE email=?");
        pst.setString(1, em);
        int isDeleted = pst.executeUpdate();
        AppointmentDAO ap = new AppointmentDAO();
        ap.delete(em);
        
        if(isDeleted == 0)
            return false;
        else
            return true;
    }
    
    public boolean updateUser(String em, String pass, String fname, String lname, String bdate) throws SQLException{
        PreparedStatement pst = con.prepareStatement("Update users SET first_name=?, last_name=?, password=?, birth_date=? WHERE email=?");
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, pass);
        pst.setString(4, bdate);
        pst.setString(5, em);
        int isUpdated = pst.executeUpdate();
        if(isUpdated == 0)
            return false;
        else
            return true;
    }
    
    public void closeConnection() throws SQLException{
        if(con != null){
            con.close();
            con = null;
        }
    }
}
