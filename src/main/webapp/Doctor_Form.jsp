<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%
    if(session.getAttribute("doctor_form") == null)
        response.sendRedirect("Controler?action=index");
    else if(((Boolean)session.getAttribute("doctor_form")).booleanValue())
        response.sendRedirect("Controler?action=index");
%>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Doctor Form</title>
    <link rel='stylesheet' href='doctor_form.css'>
    <script src='doctor_form.js'></script>

</head>

<body>
    <div class="errorDiv">
        <%
            String error = request.getParameter("error");
            if(error != null)
                out.println(error);
        %>
    </div>
    <div id='form-div'>
        <h1 id='form-heading'>Doctor's Detail Form</h1>
        <div>
            <form action='Controler' method='POST' name='doctor_detail'>
                <table>
                    <tr>
                        <td>Bachelor Degree:</td>
                        <td>
                            <Select name='deg1'>
                                <option hidden selected value=''>--Select an option--</option>
                                <option value='MBBS'>MBBS</option>
                                <option value='BDS'>BDS</option>
                            </Select>
                        </td>
                        <td>
                            <div class='error' id='deg1-error'></div>
                        </td>
                    </tr>

                    <tr>
                        <td>Institution:</td>
                        <td><input type='text' name='inst1'></td>
                        <td class='error' id='inst1-error'></td>
                    </tr>

                    <tr>
                        <td>Specialization:</td>
                        <td>
                            <Select name='deg2'>
                                <option hidden selected value=''>--Select an option--</option>
                                <option value='Dermatologist'>Dermatology</option>
                                <option value='Urologist'>Urology</option>
                                <option value='Child Specialist'>Child Specialization</option>
                                <option value='Gynecologist'>Gynecology</option>
                                <option value='Heart Specialist'>Heart Specialization</option>
                                <option value='Psychiatrist'>Psychiatry</option>
                                <option value='Orthopedist'>Orthopedic</option>
                                <option value='Eye Specialist'>Eye Specialization</option>
                                <option value='General Physician'>General Physician</option>
                            </Select>
                        </td>
                        <td class='error' id='deg2-error'></td>
                    </tr>

                    <tr>
                        <td>Institution:</td>
                        <td><input type='text' name='inst2'></td>
                        <td class='error' id='inst2-error'></td>
                    </tr>

                    <tr>
                        <td>Experience (years):</td>
                        <td><input type='number' min='0' name='experience'></td>
                        <td class='error' id='experience-error'></td>
                    </tr>

                    <tr>
                        <td><input type='radio' name='avail_hrs' class='avail-hrs' value='1'
                                style='margin-left: 162px;'></td>
                        <td class='op-txt'>6am to 2pm</td>
                    </tr>

                    <tr>
                        <td>Availability: <input type='radio' name='avail_hrs' class='avail-hrs' value='2'
                                style='margin-left: 52px;'></td>
                        <td class='op-txt'>2pm to 10pm</td>
                        <td class='error' id='avail_hrs_error'></td>
                    </tr>

                    <tr>
                        <td><input type='radio' name='avail_hrs' class='avail-hrs' value='3'
                                style='margin-left: 162px;'></td>
                        <td class='op-txt'>10pm to 6am</td>
                    </tr>

                </table>
                <div style='padding-top:17px;'>
                    <input type='submit' value='Submit' id='submit-btn' class='btn' name="action" onclick='return validate();'><br>
                    <input type='submit' value='Cancel' id='cancel-btn' class='btn' onclick='return redirect();'>
                </div>
            </form>
        </div>
    </div>
</body>

</html>
