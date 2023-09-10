function login_validate(){
    var check = true;

    if(document.login.email.value == ""){
        check = false;
        document.getElementById("email-err").innerHTML = "*email cannot be null";
    }
    else{
        document.getElementById("email-err").innerHTML = "";
    }

    if(document.login.password.value == ""){
        check = false;
        document.getElementById("password-err").innerHTML = "*password cannot be null";
    }
    else{
        document.getElementById("password-err").innerHTML = "";
    }

    return check;
}




function signup_validate(){
    var check = true;
    if(document.signup.first_name.value == "")
    {
        check = false;
        document.getElementById("first-name-err").innerHTML = "*first name cannot be null";
    }
    else{
        document.getElementById("first-name-err").innerHTML = "";
    }

    if(document.signup.last_name.value == ""){
        check = false;
        document.getElementById("last-name-err").innerHTML = "*last name cannot be null";
    }
    else{
        document.getElementById("last-name-err").innerHTML = "";
    }
    
    if(document.signup.type.value == ""){
        check = false;
        document.getElementById("type-err").innerHTML = "*please select account type";
    }
    else{
        document.getElementById("type-err").innerHTML = "";
    }

    if(document.signup.email.value == ""){
        check = false;
        document.getElementById("email-err").innerHTML = "*email cannot be null";
    }
    else{
        const validEmail = String(document.signup.email.value)
        .toLowerCase()
        .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        if(validEmail == null){
            check = false
            document.getElementById("email-err").innerHTML = "enter valid email";
        }
        else{
            document.getElementById("email-err").innerHTML = "";
        }
    }

    if(document.signup.password.value == ""){
        check = false;
        document.getElementById("password-err").innerHTML = "*password cannot be null";
    }
    else{
        document.getElementById("password-err").innerHTML = "";
    }

    if(document.signup.password.value != document.signup.confirm_password.value){
        check = false;
        document.getElementById("confirm-password-err").innerHTML = "*password and confirm password should match";
    }
    else{
        document.getElementById("confirm-password-err").innerHTML = "";
    }

    if(document.signup.gender.value == ""){
        check = false;
        document.getElementById("gender-err").innerHTML = "*please select gender";
    }
    else{
        document.getElementById("gender-err").innerHTML = "";
    }

    if(document.signup.birth_date.value == ""){
        check = false;
        document.getElementById("birth-date-err").innerHTML = "*please enter date";
    }
    else{
        document.getElementById("birth-date-err").innerHTML = "";
    }

    return check;
}

function AForm_validate(){
    var check = true;

    if(document.AppointmentForm.day.value == ""){
        check = false;
        document.getElementById("date-err").innerHTML = "*day cannot be null";
    }
    else{
        document.getElementById("date-err").innerHTML = "";
    }

    if(document.AppointmentForm.time.value == ""){
        check = false;
        document.getElementById("time-err").innerHTML = "*time cannot be null";
    }
    else{
        document.getElementById("time-err").innerHTML = "";
    }

    return check;
}
