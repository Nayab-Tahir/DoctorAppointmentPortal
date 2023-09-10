
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<%
    if(session.getAttribute("user_type") == null)
        response.sendRedirect("Controler?action=index");
    else if(!session.getAttribute("user_type").equals("patient"))
        response.sendRedirect("Controler?action=index");
    else if(request.getAttribute("list") == null){
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
    <title>Patient Home</title>
    <link rel='stylesheet' href='home.css' />
    <link rel='stylesheet' href='nav.css' />
</head>

<body>
    <%@include file="nav.html" %>
    <div id='body'>
        <div id='center-div'>
            <h1 id='app-name'>Doctor Appointment Portal</h1>
            <div id='intro-div'>
                <p id='intro-para'>
                    Find doctor and book your appointment. We have the best and reliable team of specialized doctors.
                </p>
            </div>
        </div>
        
        <%
                int[] list = (int[])request.getAttribute("list");
        %>
        
        <div class='container'>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Dermatologist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[0] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Urologist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[1] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Child Specialist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[2] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Gynecologist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[3] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Heart Specialist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[4] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Psychiatrist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[5] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Dentist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[6] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Orthopedist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[7] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>Eye Specialist</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'><%= list[8] %> </div>
            </div>
            <div class='card' onclick='lnk(this)'>
                <div class='card-name'>General Physician</div>
                <div class='card-text'>Available Doctors</div>
                <div class='card-no'> <%= list[9] %> </div>
            </div>

        </div>

        <div style='text-align: center;'>
            <button id='show-btn' onclick='show(this)'>Show</button>
        </div>
    </div>

    <form id='myform' action='Controler?action=Category' method='POST'>
        <input type='hidden' name='category' value=''>
    </form>



    <script src="home.js"></script>
</body>

</html>

<% } %>