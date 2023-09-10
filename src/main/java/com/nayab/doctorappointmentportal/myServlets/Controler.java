package com.nayab.doctorappointmentportal.myServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.nayab.doctorappointmentportal.myClasses.Appointment;
import com.nayab.doctorappointmentportal.myClasses.AppointmentDAO;
import com.nayab.doctorappointmentportal.myClasses.Doctor;
import com.nayab.doctorappointmentportal.myClasses.DoctorDAO;
import com.nayab.doctorappointmentportal.myClasses.DoctorForm;
import com.nayab.doctorappointmentportal.myClasses.DoctorFormDAO;
import com.nayab.doctorappointmentportal.myClasses.UserDAO;
import com.nayab.doctorappointmentportal.myClasses.UserInfo;

public class Controler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String action = request.getParameter("action");
        
        switch (action) {
            case "index":
                directHome(request, response);
                break;
            case "Signup":
                boolean isInserted = insertUser(request, response);
                if(isInserted){
                    directHome(request, response);
                }   break;
            case "Login":
                boolean logedIn = login(request, response);
                if(logedIn){
                    directHome(request, response);
                }
                break;
            case "Logout":
                Logout(request, response);
                break;
            case "Submit":
                doctor_form_submission(request, response);
                break;
            case "Category":
                Category(request, response);
                break;
            case "Request Appointment":
                RequestDispatcher rd = request.getRequestDispatcher("AppointmentForm.jsp");
                rd.forward(request, response);
                break;
            case "Request":
                boolean check = requesting(request, response);
                if(check)
                    allAppointments(request, response);
                break;
            case "AllAppointments":
                allAppointments(request, response);
                break;
            case "CancelAppointment":
                updateAppointment(request, response, "canceled");
                break;
            case "AcceptAppointment":
                updateAppointment(request, response, "accepted");
                break;
            case "RejectAppointment":
                updateAppointment(request, response, "rejected");
                break;
            case "ProcessAppointment":
                updateAppointment(request, response, "processed");
                break;
            case "Delete":
                boolean isDeleted = delete(request, response);
                if(isDeleted)
                    directHome(request, response);
                break;
            case "Update":
                update(request, response);
                break;
            case "Confirm":
                confirm(request, response);
                break;
            default:
                break;
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        processRequest(request, response);
    }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        processRequest(request, response);
    }
    
    private boolean insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        boolean isInserted = false;
        try{
            UserInfo user = new UserInfo();
            
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String type = request.getParameter("type");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirm_password");
            String gender = request.getParameter("gender");
            String birthDate = request.getParameter("birth_date");
            
            if(firstName.length() > 0 && lastName.length() > 0 && email.length() > 0 && password.length() > 0 &&
                password.equals(confirm_password) && (gender.equals("male") || gender.equals("female")) &&
                birthDate.length() > 0 && (type.equals("doctor") || type.equals("patient")) ){

                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                if(pattern.matcher(email).matches()){
                  
                    user.setFirst_name(firstName);
                    user.setLast_name(lastName);
                    user.setType(type);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setGender(gender);
                    user.setDate(birthDate);
                    
                    UserDAO userDAO = new UserDAO();
                    boolean exists = userDAO.userExists(user.getEmail());
                    
                    if(!exists){
                        isInserted = userDAO.insertUser(user);
                        if(isInserted){
                            HttpSession session = request.getSession();
                            session.setAttribute("user_type", user.getType());
                            session.setAttribute("user_email", user.getEmail());
                            session.setAttribute("doctor_form", user.isForm());
                        }
                    }
                    else{
                        response.sendRedirect("Signup.jsp?error=*User with same name exists");
                    }
                }
                else{
                    response.sendRedirect("Signup.jsp?error=*Wrong Email Format");
                }
            }
            else{
                response.sendRedirect("Signup.jsp?error=*Your Data is in Wrong Format");
            }
             
        }catch(SQLException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
        catch(ClassNotFoundException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
        
        return isInserted;
    }
    
    private void directHome(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        HttpSession session = request.getSession(false);
            if(session == null){
                response.sendRedirect("Login.jsp");
            }
            else{
                if(session.getAttribute("user_type") == null){
                    response.sendRedirect("Login.jsp");
                    return;
                }
                switch (session.getAttribute("user_type").toString()) {
                    case "patient":
                        try{
                            DoctorFormDAO form = new DoctorFormDAO();
                            request.setAttribute("list", form.getTotalDoctorNumber());
                            RequestDispatcher rd = request.getRequestDispatcher("Patient_Home.jsp");
                            rd.forward(request, response);
                        }catch(ClassNotFoundException ex){
                        }catch(SQLException ex){}
                        break;
                    case "doctor":
                        if(((Boolean)session.getAttribute("doctor_form")).booleanValue())
                            allAppointments(request, response);
                        else
                            response.sendRedirect("Doctor_Form.jsp");
                        break;
                    case "admin":
                        adminPage(request, response);
                        break;
                    default:
                        response.sendRedirect("Login.jsp");
                        break;
                }
            }
    }
    
    private boolean login(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        boolean logedIn= false;
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if(email.length() > 0 && password.length() > 0){
                UserDAO userDAO = new UserDAO();
                UserInfo user = userDAO.searchUser(email, password);
                userDAO.closeConnection();
                
                if(user != null){
                    logedIn = true;
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(24*60*60);
                    session.setAttribute("user_email", user.getEmail());
                    session.setAttribute("user_type", user.getType());
                    session.setAttribute("doctor_form", user.isForm());
                }
                else{
                    response.sendRedirect("Login.jsp?error=*Wrong email or password");
                }
            }
            else{
                response.sendRedirect("Login.jsp?error=*Email and password can't be null");
            }
        }catch(SQLException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
        catch(ClassNotFoundException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
        
        return logedIn;
    }

    private void Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            response.sendRedirect("Login.jsp");
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }

    private void doctor_form_submission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            String deg1 = request.getParameter("deg1");
            String inst1 = request.getParameter("inst1");
            String deg2 = request.getParameter("deg2");
            String inst2 = request.getParameter("inst2");
            String experience_str = request.getParameter("experience");
            String avail_hrs_str = request.getParameter("avail_hrs");
            int experience = 0;
            int avail_hrs = 0;
            try{
                experience = Integer.parseInt(experience_str);
                avail_hrs = Integer.parseInt(avail_hrs_str);
            }catch(NumberFormatException ex){
                response.sendRedirect("Doctor_Form.jsp?error=*Please select valid options");
            }
            
            if(deg1.length() > 0 && inst1.length() > 1 && ((deg2.length() > 0 && inst2.length() > 0) || (deg2.length() == 0 && inst2.length() == 0))
                   && experience > 0 && avail_hrs > 0 ){
            
                try{
                    String email = session.getAttribute("user_email").toString();
                    DoctorForm df = new DoctorForm();
                    
                    df.setEmail(email);
                    df.setDegree1(deg1);
                    df.setInstitution1(inst1);
                    df.setDegree2(deg2);
                    df.setInstitution2(inst2);
                    df.setExperience(experience);
                    df.setAvailability(avail_hrs);

                    DoctorFormDAO form = new DoctorFormDAO();
                    boolean is_updated = form.insertDoctorForm(df);
                    form.closeConnection();
                    
                    if(is_updated){
                        UserDAO u = new UserDAO();
                        boolean isUpdated = u.updateUser_form(true, email);
                        u.closeConnection();
                        
                        if(isUpdated){
                            session.setAttribute("doctor_form", true);
                            response.sendRedirect("Doctor_Home.jsp");
                        }
                        else
                            response.sendRedirect("Doctor_Form.jsp?error=*Failed to Update Form Column in Users");
                    }
                    else
                        response.sendRedirect("Doctor_Form.jsp?error=*Failed to Update Form");
                }
                catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else
                response.sendRedirect("Doctor_Form.jsp?error=*Data is in Wrong Format");
        }
        else
            response.sendRedirect("Login.html");
    }

    private void Category(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            String cat = request.getParameter("category");
            if(cat != null && session.getAttribute("user_type") != null && session.getAttribute("user_type").toString().equals("patient")){
                try{
                DoctorDAO d = new DoctorDAO();
                ArrayList<Doctor> doctors;
                
                if(cat.equals("Dentist"))
                    doctors = d.searchDoctor("BDS");
                else
                    doctors = d.searchDoctor("MBBS", cat);
                
                request.setAttribute("doctors", doctors);
                RequestDispatcher rd = request.getRequestDispatcher("Category.jsp");
                rd.forward(request, response);
                    
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }

    private boolean requesting(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean isInserted = false;
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("user_type") != null && session.getAttribute("user_type").toString().equals("patient")){
                if(request.getParameter("doctor") != "" && request.getParameter("day") != "" && request.getParameter("time") != ""){
                    try{
                        Appointment ap = new Appointment();
                        ap.setDoctor(request.getParameter("doctor"));
                        ap.setPatient(session.getAttribute("user_email").toString());
                        ap.setDay(request.getParameter("day"));
                        ap.setTime(request.getParameter("time"));
                        ap.setStatus("waiting");
                        AppointmentDAO apDAO = new AppointmentDAO();
                        isInserted = apDAO.request(ap);
                        
                    }catch(SQLException ex){
                        request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                        RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                        rd.forward(request,response);
                    }
                    catch(ClassNotFoundException ex){
                        request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                        RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                        rd.forward(request,response);
                    }
                }else{
                    String email = request.getParameter("doctor");
                    response.sendRedirect("AppointmentForm.jsp?error=*invalid data format&email="+email);
                }
            }else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
        
        return isInserted;
    }

    private void allAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("user_type") != null && (session.getAttribute("user_type").toString().equals("patient") || session.getAttribute("user_type").toString().equals("doctor")) ){
                try{
                    AppointmentDAO apDAO = new AppointmentDAO();
                    String user_type = session.getAttribute("user_type").toString();
                    
                    ArrayList<Appointment> list = null;
                    if(user_type.equals("patient"))
                        list = apDAO.search( session.getAttribute("user_email").toString() );
                    else if(user_type.equals("doctor"))
                        list = apDAO.search2( session.getAttribute("user_email").toString() );
                    request.setAttribute("list", list);
                    
                    RequestDispatcher rd = null;
                    if(user_type.equals("patient"))
                        rd = request.getRequestDispatcher("AllAppointments.jsp");
                    else if(user_type.equals("doctor"))
                        rd = request.getRequestDispatcher("Doctor_Home.jsp");
                    
                    rd.forward(request, response);
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response, String st) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("user_type") != null && (session.getAttribute("user_type").toString().equals("patient") || session.getAttribute("user_type").toString().equals("doctor"))
                    && request.getParameter("appointmentNo") != null){
                try{
                    AppointmentDAO apDAO = new AppointmentDAO();
                    String user_type = session.getAttribute("user_type").toString();
                    
                    ArrayList<Appointment> list = null;
                    if(user_type.equals("patient"))
                        list = apDAO.search( session.getAttribute("user_email").toString() );
                    else if(user_type.equals("doctor"))
                        list = apDAO.search2( session.getAttribute("user_email").toString() );
                    Appointment ap = list.get(Integer.parseInt(request.getParameter("appointmentNo")));
                    
                    if(st.equals("canceled")){
                        if(ap.getStatus().equals("waiting") ||ap.getStatus().equals("accepted")){
                            ap.setStatus(st);
                            apDAO.updateRequest(ap);
                        }
                    }
                    else if(st.equals("accepted")){
                        if(ap.getStatus().equals("waiting")){
                            ap.setStatus(st);
                            apDAO.updateRequest(ap);
                        }
                    }
                    else if(st.equals("rejected")){
                        if(ap.getStatus().equals("waiting")){
                            ap.setStatus(st);
                            apDAO.updateRequest(ap);
                        }
                    }
                    else if(st.equals("processed")){
                        if(ap.getStatus().equals("accepted")){
                            ap.setStatus(st);
                            apDAO.updateRequest(ap);
                        }
                    }
                    allAppointments(request, response);
                    
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }

    private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ArrayList<Doctor> docList = null;
            DoctorDAO docDAO = new DoctorDAO();
            docList = docDAO.allDoctors();
            request.setAttribute("docList", docList);
            
            ArrayList<UserInfo> patList = null;
            UserDAO patDAO = new UserDAO();
            patList = patDAO.allPatients();
            request.setAttribute("patList", patList);
            
            RequestDispatcher rd = request.getRequestDispatcher("Admin_Home.jsp");
            rd.forward(request, response);
            
        }catch(SQLException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
        catch(ClassNotFoundException ex){
            request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
            RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
            rd.forward(request,response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("user_type") != null && (session.getAttribute("user_type").toString().equals("admin"))
                    && request.getParameter("userNo") != null && request.getParameter("type") != "" ){
                try {
                    if(request.getParameter("type").equals("doctor")){
                        ArrayList<Doctor> docList = null;
                        DoctorDAO docDAO = new DoctorDAO();
                        docList = docDAO.allDoctors();
                        Doctor doc = docList.get(Integer.parseInt(request.getParameter("userNo")));
                        UserDAO userDAO = new UserDAO();
                        UserInfo user = userDAO.searchUser(doc.getEmail());
                        request.setAttribute("user", user);
                    }
                    else if(request.getParameter("type").equals("patient")){
                        ArrayList<UserInfo> patList = null;
                        UserDAO patDAO = new UserDAO();
                        patList = patDAO.allPatients();
                        UserInfo pat = patList.get(Integer.parseInt(request.getParameter("userNo")));
                        request.setAttribute("user", pat);
                    }
                            
                    RequestDispatcher rd  = request.getRequestDispatcher("Admin_Update_Page.jsp");
                    rd.forward(request, response);
                        
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }

    private boolean delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean check = false;
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("user_type") != null && (session.getAttribute("user_type").toString().equals("admin"))
                    && request.getParameter("userNo") != "" && request.getParameter("type") != "" ){
               
                try {
                    if(request.getParameter("type").equals("doctor")){
                        ArrayList<Doctor> docList = null;
                        DoctorDAO docDAO = new DoctorDAO();
                        docList = docDAO.allDoctors();
                        Doctor doc = docList.get(Integer.parseInt(request.getParameter("userNo")));
                        check = docDAO.deleteDoctor(doc);
                    }
                    else if(request.getParameter("type").equals("patient")){
                        ArrayList<UserInfo> patList = null;
                        UserDAO patDAO = new UserDAO();
                        patList = patDAO.allPatients();
                        UserInfo pat = patList.get(Integer.parseInt(request.getParameter("userNo")));
                        check = patDAO.deleteUser(pat.getEmail());
                    }
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            response.sendRedirect("Login.jsp");
        }
        
        return check;
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user_type") != null && (session.getAttribute("user_type").toString().equals("admin"))
               && request.getParameter("user") != null ){
            
            String fname = request.getParameter("first_name");
            String lname = request.getParameter("last_name");
            String pass = request.getParameter("password");
            String bdate = request.getParameter("birth_date");
            String em = request.getParameter("user");
            
            if(request.getParameter("first_name") != null && request.getParameter("last_name") != null && request.getParameter("password") != null
                    && request.getParameter("birth_date") != null ){
                try{
                    UserDAO userDAO = new UserDAO();
                    userDAO.updateUser(em, pass, fname, lname, bdate);
                    directHome(request, response);
                    
                }catch(SQLException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
                catch(ClassNotFoundException ex){
                    request.setAttribute("jakarta.servlet.jsp.JspException" , ex); 
                    RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                    rd.forward(request,response);
                }
            }
            else{
                directHome(request, response);
            }
        }
        else{
            directHome(request, response);
        }
    }

}
