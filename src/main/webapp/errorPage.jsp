<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<% if(exception == null){
        response.sendRedirect("Controler?action=index");
    }else{
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        <h3> 
        <% if (exception instanceof SQLException) { %> 

        An SQL Exception 

        <% } else if (exception instanceof ClassNotFoundException){ %> 

        A Class Not Found Exception 

        <%} else { %> 
        A Exception 

        <% } %> 
        occured while interacting with the database</h3> 

        <h3>The Error Message was <%= exception.getMessage() %></h3>
        <h3 > Please Try Again Later! </h3> 

        <h3> <a href="Controler?action=index"> Home </a> 
    </body>
</html>

<% } %>
