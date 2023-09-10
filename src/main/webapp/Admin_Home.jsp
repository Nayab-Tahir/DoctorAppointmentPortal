
<%@page import="myClasses.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.nayab.doctorappointmentportal.myClasses.Doctor"%>
<%@page import="java.util.ArrayList"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("admin"))
        response.sendRedirect("Controler?action=index");
    else if(request.getAttribute("docList") == null || request.getAttribute("patList") == null){
        response.sendRedirect("Controler?action=index");
    }
    else{
%>

<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Admin Home</title>
    <link rel='stylesheet' href='home.css'>
    <link rel='stylesheet' href='nav.css'>
</head>

<style>
    .doctors > div{
        height: auto;
    }
</style>

<body>
    <%@include file="admin_nav.html" %>
    
    <div class='cat-name'>Doctor Appointment Portal</div>
    <div class='doctors' id="Doc">
        <div id="type_doc1">
            <h1>
                <a class='specific_doctor_link' href='#Doc'> Doctors </a>
            </h1>
       </div>
        <%
            int avail = 0;
            ArrayList<Doctor> doctors = (ArrayList<Doctor>)request.getAttribute("docList");
            Doctor doc = null;
            for(int i=0; i<doctors.size(); i++){ 
                doc = doctors.get(i);
        %>
            <div>
                <h2>
                    <a class='specific_doctor_link' href='#'> <%= doc.getFirst_name()+ " " + doc.getLast_name() %> </a>
                </h2>
                <table>
                    <tr>
                        <td>Degree: </td>
                        <td><%= doc.getDegree1() %></td>
                    </tr>
                    <tr>
                        <td>From: </td>
                        <td><%= doc.getInstitution1() %></td>
                    </tr>

                    <tr>
                        <td>Specialization: </td>
                        <td><%= doc.getDegree2() %></td>
                    </tr>
                    <tr>
                        <td>From: </td>
                        <td><%= doc.getInstitution2() %></td>
                    </tr>

                    <tr>
                        <td>Gender: </td>
                        <td><%= doc.getGender() %></td>
                    </tr>
                    <tr>
                    <tr>
                        <td>Experience: </td>
                        <td><%= doc.getExperience() + " Years" %></td>
                    </tr>
                    <tr>
                        <td>Availability: </td>
                        <% avail=doc.getAvailability();
                            if(avail==1){
                        %>
                            <td>6am to 2pm</td>
                        <%
                            }else if(avail == 2){
                        %>
                            <td>2pm to 10pm</td>
                        <%
                            }else if(avail == 3){
                        %>
                            <td>10pm to 6am</td>
                        <% } %>
                    </tr>
                </table>
                <div>
                    <form action="Controler" method="POST" style="text-align:start">
                        <input type="hidden" name="userNo" value=<%= i %>>
                        <input type="hidden" name="type" value="doctor">
                        <input type="submit" value="Update" name="action" style="margin: 10px 30px">
                        <input type="submit" value="Delete" name="action" style="margin: 10px 18px">
                    </form>
                </div>
            </div>
        <% } %>
        <div id="type_doc2">
            <h1>
                <a id="Pat" class='specific_doctor_link' href='#Pat'> Patients </a>
            </h1>
        </div>
        <%
            avail = 0;
            ArrayList<UserInfo> patients = (ArrayList<UserInfo>)request.getAttribute("patList");
            UserInfo pat = null;
            for(int i=0; i<patients.size(); i++){ 
                pat = patients.get(i);
        %>
        <div>
                <h2>
                    <a class='specific_doctor_link' href='#'> <%= pat.getFirst_name()+ " " + pat.getLast_name() %> </a>
                </h2>
                <table>
                    <tr>
                        <td>Gender: </td>
                        <td><%= pat.getGender() %></td>
                    </tr>
                    <tr>
                        <td>Birth Date: </td>
                        <td><%= pat.getBirth_date() %></td>
                    </tr>
                </table>
                <div>
                    <form action="Controler" method="POST" style="text-align:start">
                        <input type="hidden" name="userNo" value=<%= i %>>
                        <input type="hidden" name="type" value="patient">
                        <input type="submit" value="Update" name="action" style="margin: 10px 30px">
                        <input type="submit" value="Delete" name="action" style="margin: 10px 18px">
                    </form>
                </div>
            </div>
        <% } %>
    </div>
</body>

</html>

<% } %>

