<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="main.css">
    <script src="main.js"></script>
</head>

<style>
    td > input{
        width: 210px;
    }
</style>

<body>
    <div class="error">
        <%
            String error = request.getParameter("error");
            if(error != null)
                out.println(error);
        %>
    </div>
    <div id="login-page" class="page">
        <div class="heading">Login</div>
            <form action="Controler" method="POST" name="login" onsubmit="return login_validate()">
                <table style="border-spacing: 0px 5px;">
                    <tr>
                        <td>Email Address:</td>
                        <td><input type="email" name="email"></td>
                        <td class="error-message" id="email-err"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password"></td>
                        <td class="error-message" id="password-err"></td>
                    </tr>
                </table>
                <input type="submit" value="Login" name="action">
               
            </form>
            <div class="other-div" >
                Don't have Account? <a class='other-btn' href="Signup.jsp">Create Account</a>
            </div>
    </div>
</body>
</html>
