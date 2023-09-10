<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="main.css">
    <script src="main.js"></script>
</head>


<body>
    <div class="error">
        <%
            String error = request.getParameter("error");
            if(error != null)
                out.println(error);
        %>
    </div>
    <div id="signup-page" class="page">
        <div class="heading">Sign Up</div>
            <form action="Controler" method="POST" name="signup" onsubmit="return signup_validate()">
                <table style="border-spacing: 0px 5px;">
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="first_name"></td>
                        <td class="error-message" id="first-name-err"></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="last_name"></td>
                        <td class="error-message" id="last-name-err"></td>
                    </tr>
                    <tr>
                        <td>Account Type:</td>
                        <td><input type="radio" name="type" value="doctor" style="width: 20px;">Doctor<input type="radio" name="type" value="patient" style="width: 20px;">Patient</td>
                        <td class="error-message" id="type-err"></td>
                    </tr>
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
                    <tr>
                        <td>Confirm Password:</td>
                        <td><input type="password" name="confirm_password"></td>
                        <td class="error-message" id="confirm-password-err"></td>
                    </tr>
                    <tr>
                        <td>Gender:</td>
                        <td><input type="radio" name="gender" value="male" style="width: 20px;">Male<input type="radio" name="gender" value="female" style="width: 20px;">Female</td>
                        <td class="error-message" id="gender-err"></td>
                    </tr>
                    <tr>
                        <td>Date of Birth:</td>
                        <td><input type="date" name="birth_date"></td>
                        <td class="error-message" id="birth-date-err"></td>
                    </tr>
                </table>
                <input type="submit" value="Signup" name="action">
            </form>
            <div id="other-div-login" class="other-div">
                Already have Account? <a class='other-btn' href="Login.jsp">Sign In</a>
            </div>

    </div>
</body>
</html>