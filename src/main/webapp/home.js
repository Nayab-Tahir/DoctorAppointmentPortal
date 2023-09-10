
    var i = 11;
    var cn = document.getElementsByClassName("container")[0].childNodes;
    while(i<20){
        cn[i].style.display = "none";
        i = i + 2;
    }

    function show(n){
        var j = 11;
        var cn = document.getElementsByClassName("container")[0].childNodes;
        if(cn[11].style.display === "none"){
            document.getElementsByClassName("container")[0].style.marginTop = "0px";
            while(j<20){
                cn[j].style.display = "block";
                j = j + 2;
            }
            n.innerHTML = "Hide";
        }
        else{
            document.getElementsByClassName("container")[0].style.marginTop = "88px";
            while(j<20){
                cn[j].style.display = "none";
                j = j + 2;
            }
            n.innerHTML = "Show";
        }
    }

    function lnk(e){
        var link = e.childNodes[1].innerHTML;
        var node = document.getElementById("myform");
        node.category.value = link;
        node.submit();
    }