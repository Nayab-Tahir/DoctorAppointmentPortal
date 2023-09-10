
<%@page import="java.util.ArrayList"%>
<%@page import="com.nayab.doctorappointmentportal.myClasses.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("doctor"))
        response.sendRedirect("Controler?action=index");
    else if(request.getAttribute("list") == null){
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
    <title>Home</title>
    <link rel='stylesheet' href='home.css' />
    <link rel='stylesheet' href='nav.css' />
</head>

<body>
    <%@include file="doc_nav.html" %>
    <div class='cat-name'>All Appointments</div>
    <div class='appointments'>

    <%
        for(int i=0; i<list.size(); i++){
            Appointment ap = list.get(i);
    %>
        <div>
            <table>
                <tr>
                    <td>Patient: </td>
                    <td><%= ap.getPatient() %></td>
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
            <% if(ap.getStatus().equals("accepted")){ %>
                <form action="Controler" method="POST" style="text-align:start">
                    <input type="hidden" name="appointmentNo" value=<%= i %>>
                    <input type="submit" value="ProcessAppointment" name="action" style="margin: 10px 30px">
                    <input type="submit" value="CancelAppointment" name="action" style="margin: 10px 18px">
                </form>
            <% }else if(ap.getStatus().equals("waiting")){ %>
                <form action="Controler" method="POST" style="text-align:start">
                    <input type="hidden" name="appointmentNo" value=<%= i %>>
                    <input type="submit" value="AcceptAppointment" name="action" style="margin: 10px 30px">
                    <input type="submit" value="RejectAppointment" name="action" style="margin: 10px 18px">
                </form>
            <% } %>
        </div>
    <% } %>
            
    </div>
</body>
</html>

<% } %>