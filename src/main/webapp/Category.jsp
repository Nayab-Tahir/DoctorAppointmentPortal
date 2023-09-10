<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.nayab.doctorappointmentportal.myClasses.Doctor"%>
<%@page import="java.util.ArrayList"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("patient"))
        response.sendRedirect("Controler?action=index");
    else if(request.getAttribute("doctors") == null){
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
    <title>Category Doctors</title>
    <link rel='stylesheet' href='home.css'>
    <link rel='stylesheet' href='nav.css'>
</head>

<body>
    <%@include file="nav.html" %>
    <div class='cat-name'><%= request.getParameter("category") %></div>
    <div class='doctors'>
        <%
            int avail=0;
            ArrayList<Doctor> doctors = (ArrayList<Doctor>)request.getAttribute("doctors");
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
                        <form action="Controler" action="POST">
                            <input type="hidden" name="email" value="<%= doc.getEmail() %>">
                            <input type="submit" name="action" value="Request Appointment">
                        </form>
                    </div>
            </div>
        <% } %>
    </div>
</body>

</html>

<% } %>
