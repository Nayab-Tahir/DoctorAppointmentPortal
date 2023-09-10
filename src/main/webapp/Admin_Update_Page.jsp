<%@page import="com.nayab.doctorappointmentportal.myClasses.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("admin"))
        response.sendRedirect("Controler?action=index");
    else if(request.getAttribute("user") == null){
        response.sendRedirect("Controler?action=index");
    }
    else{
        UserInfo user = (UserInfo)request.getAttribute("user");
%>

<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Admin Update Page</title>
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
    <div class="error" style="margin-top: 54px">
        <%
            if(request.getParameter("error") != null){
                out.println(request.getParameter("error"));
            }
        %>
    </div>
    <div class='doctors' id="Doc" >
        
        <div id="AFormPage" class="page">
            <div id="type_doc1">
                <h1>
                    <a class='specific_doctor_link' href='#Doc'> Update User Data </a>
                </h1>
            </div>
                <form action="Controler" method="POST" name="AppointmentForm" onsubmit="return AForm_validate()">
                    <table style="border-spacing: 0px 5px;">
                        <tr>
                            <td>First Name:</td>
                            <td><input type="text" name="first_name" value="<%= user.getFirst_name() %>"></td>
                            <td class="error-message" id="first-name-err"></td>
                        </tr>
                        <tr>
                            <td>Last Name:</td>
                            <td><input type="text" name="last_name" value="<%= user.getLast_name() %>"></td>
                            <td class="error-message" id="last-name-err"></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="password" value="<%= user.getPassword() %>"></td>
                            <td class="error-message" id="password-err"></td>
                        </tr>
                        <tr>
                            <td>BirthDate:</td>
                            <td><input type="date" name="birth_date" value="<%= user.getBirth_date() %>" ></td>
                            <td class="error-message" id="birth-date-err"></td>
                        </tr>
                    </table>
                    <input type="hidden" name="user" value="<%= user.getEmail() %>">
                    <input type="submit" value="Confirm" name="action" style="margin: 20px 146px">
                   
                </form>
        </div>
    </div>
</body>
</html>



<% } %>
