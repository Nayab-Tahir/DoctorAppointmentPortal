function redirect(){
        window.location.href = "Controler?action=Logout";
        return false;
    }

    function validate(){
        var check = true;
        var deg1 = document.doctor_detail.deg1.value;
        var deg2 = document.doctor_detail.deg2.value;
        var inst1 = document.doctor_detail.inst1.value;
        var inst2 = document.doctor_detail.inst2.value;
        var exp = document.doctor_detail.experience.value;
        var avail_hrs = document.doctor_detail.avail_hrs.value;
        
        if(deg1 == ""){
            document.getElementById("deg1-error").innerHTML = "*please select your degree";
            check = false;
        }
        else{
            document.getElementById("deg1-error").innerHTML = "";
        }

        if(inst1 == ""){
            document.getElementById("inst1-error").innerHTML = "*please enter institution name";
            check = false;
        }
        else{
            document.getElementById("inst1-error").innerHTML = "";
        }

        if(deg1 == "BDS"){
            if( (inst2 == "" && deg2 != "") || (inst2 != "" && deg2 == "") ){
                if(inst2 == "")
                    document.getElementById("inst2-error").innerHTML = "*please enter your institution name";
                else
                    document.getElementById("deg2-error").innerHTML = "*please select your degree";
                check = false;
            }
            else{
                document.getElementById("inst2-error").innerHTML = "";
                document.getElementById("deg2-error").innerHTML = "";
            }
        }
        else if(deg1 == "MBBS"){
            if(deg2 == ""){
                document.getElementById("deg2-error").innerHTML = "*please select your degree";
                check = false;
            }
            else{
                document.getElementById("deg2-error").innerHTML = "";
            }
            
            if(inst2 == ""){
                document.getElementById("inst2-error").innerHTML = "*please enter your institution name";
                check = false;
            }
            else{
                document.getElementById("inst2-error").innerHTML = "";
            }
        }
        else{
            document.getElementById("inst2-error").innerHTML = "";
            document.getElementById("deg2-error").innerHTML = "";
        }

        if(exp == ""){
            document.getElementById("experience-error").innerHTML = "*please enter your experience";
            check = false;
        }
        else{
            var n = Number(exp);
            if(n != NaN && n > -1)
                document.getElementById("experience-error").innerHTML = "";
            else{
                document.getElementById("experience-error").innerHTML = "*enter valid number";
            }
        }

        if(avail_hrs == ""){
            document.getElementById("avail_hrs_error").innerHTML = "*please select your availability choice";
            check = false;
        }
        else{
            document.getElementById("avail_hrs_error").innerHTML = "";
        }

        return check;
    }