//var contactRequest = new XMLHttpRequest();

var request = new XMLHttpRequest();


function getContactInformation() {
    showInputs();
    /*
        contactRequest.open("GET", "/contactsList/getContactById", true);
        contactRequest.onreadystatechange = showInputs;
        contactRequest.send(null);
    */

}

function showButtons() {
    var buttonHTML = "<form>" +
        "<button type=\"submit\" id= 'save-contact'>Save</button> " +
        "<button type=\"reset\" id= 'cancel-creator'>Cancel</button> " +
        "</form>";
}

function showInputs() {
    var contact = JSON.parse(respones);
    var textHTML = "<form> " +
        "<label> " +
        "First name*:" +
        "<br>" +
        "<input name='firstName' type=\"text\"  required value= '" + contact.firstName + "'> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Middle name:" +
        "<br>" +
        "<input name='middleName' type=\"text\" value= '" + contact.middleName + "'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Last name*:" +
        "<br>" +
        "<input name='lastName' type=\"text\"  required value= '" + contact.lastName + "'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Gender:" +
        "<br>" +
        "<input type=\"text\"  value= '" + contact.gender + "'> " +
        "</label> " +
        "<br>" +
        "Citizenship:" +
        "<br>" +
        "<input type=\"text\"  value= '" + contact.citizenship + "'> " +
        "</label> " +
        "<br>" +
        "Web Site:" +
        "<br>" +
        "<input type=\"text\"  value= '" + contact.webSite + "'> " +
        "</label> " +
        "<br>" +
        "email:" +
        "<br>" +
        "<input type=\"text\"  value= '" + contact.email + "'> " +
        "</label> " +
        "<br>" +
        "Company:" +
        "<br>" +
        "<input type=\"text\"  value= '" + contact.company + "'> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "<button type=\"submit\">Save</button> " +
        "</label> " +
        "</form>";

    document.getElementById("inputs").innerHTML = textHTML;
}