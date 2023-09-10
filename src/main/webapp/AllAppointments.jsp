<%@page import="com.nayab.doctorappointmentportal.myClasses.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("patient"))
        response.sendRedirect("Controler?action=index");
    else if((ArrayList<Appointment>)request.getAttribute("list") == null){
        response.sendRedirect("Controler?action=index");
    }
    else{
        ArrayList<Appointment> list = (ArrayList<Appointment>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Appointments</title>
    <link rel='stylesheet' href='nav.css'>
    <link rel='stylesheet' href='home.css'>
</head>

<body>
    <%@include file="nav.html" %>
    <div class='cat-name'>All Appointments</div>
    <div class='appointments'>

    <%
        for(int i=0; i<list.size(); i++){
            Appointment ap = list.get(i);
    %>
        <div>
            <table>
                <tr>
                    <td>Doctor: </td>
                    <td><%= ap.getDoctor() %></td>
                </tr>
                <tr>
                    <td>Date: </td>
                    <td><%= ap.getDay() %></td>
                </tr>

                <tr>
                    <td>Time: </td>
                    <td><%= ap.getTime() %></td>
                </tr>
                <tr>
                    <td>Status: </td>
                    <td><%= ap.getStatus() %></td>
                </tr>
            </table>
            <% if(ap.getStatus().equals("waiting") || ap.getStatus().equals("accepted")){ %>
                <form action="Controler" method="POST">
                    <input type="hidden" name="appointmentNo" value=<%= i %>>
                    <input type="hidden" name="action" value="CancelAppointment">
                    <input type="submit" value="Cancel" style="margin: 10px 146px">
                </form>
            <% } %>
        </div>
    <% } %>
            
    </div>
</body>
</html>

<% } %>
