var contactRequest = new XMLHttpRequest();

function getCheckbox() {
    var table = document.getElementById("contact-table"),
        checks = table.getElementsByTagName("checkbox");
    for (var i = 0; i < checks.length; i++){
        if(checks[i].checked){
            i--;
        }
    }

}

function deleteContact() {

}