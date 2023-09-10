<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("patient"))
        response.sendRedirect("Controler?action=index");
    else if(request.getParameter("email") == null){
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
    <title>Appointments</title>
    <link rel='stylesheet' href='nav.css'>
    <link rel="stylesheet" href="main.css">
    <script src="main.js"></script>
</head>

<body>
    <%@include file="nav.html" %>
    <div>
        <div class="error" style="margin-top: 54px">
            <%
                if(request.getParameter("error") != null){
                    out.println(request.getParameter("error"));
                }
            %>
        </div>
        <div id="AFormPage" class="page">
            <div class="heading">Appointment</div>
                <form action="Controler" method="POST" name="AppointmentForm" onsubmit="return AForm_validate()">
                    <table style="border-spacing: 0px 5px;">
                        <tr>
                            <td>Day of Appointment:</td>
                            <td><input type="date" name="day"></td>
                            <td class="error-message" id="date-err"></td>
                        </tr>
                        <tr>
                            <td>Time of Appointment:</td>
                            <td><input type="time" name="time"></td>
                            <td class="error-message" id="time-err"></td>
                        </tr>
                    </table>
                    <input type="hidden" name="doctor" value="<%= request.getParameter("email") %>">
                    <input type="submit" value="Request" name="action" style="margin: 20px 146px">
                   
                </form>
        </div>
    </div>
</body>
</html>

<% } %>
