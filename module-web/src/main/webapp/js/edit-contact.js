function loadEditor(id) {
    document.getElementById("main-contact").style.display = 'none';
    document.getElementById("contact-editor").style.display = 'block';
    showButtons();
    fillInputs(id);
}

function showButtons() {
    var buttonHTML = "<form>" +
        "<button type=\"submit\" id= 'save-contact' onclick='saveButton()'>" +
        "Save" +
        "</button> " +
        "<button type=\"reset\" id= 'cancel-creator' onclick='cancelButton()'>" +
        "Cancel" +
        "</button> " +
        "</form>";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

async function fillInputs(id) {
    var contactId = id;

    const response = await fetch("application?command=getContactById", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contactId)
    }).then(async response => {
            const json = await response.json();
            console.log(json);
        }
    ).catch(function (error) {
            console.log(error);
        });

}

function showInputs() {
    var textHTML = "<form> " +
        // "<label> " +
        // "First name*:" +
        // "<br>" +
        // "<input name='firstName' type=\"text\"  required value= '" + contact.firstName + "'> " +
        // "</label> " +
        // "<br>" +
        // "<label> " +
        // "Middle name:" +
        // "<br>" +
        // "<input name='middleName' type=\"text\" value= '" + contact.middleName + "'>" +
        // "</label> " +
        // "<br>" +
        // "<label> " +
        // "Last name*:" +
        // "<br>" +
        // "<input name='lastName' type=\"text\"  required value= '" + contact.lastName + "'> " +
        // "</label> " +
        // "<label> " +
        // "<br>" +
        // "Gender:" +
        // "<br>" +
        // "<input type=\"text\"  value= '" + contact.gender + "'> " +
        // "</label> " +
        // "<br>" +
        // "Citizenship:" +
        // "<br>" +
        // "<input type=\"text\"  value= '" + contact.citizenship + "'> " +
        // "</label> " +
        // "<br>" +
        // "Web Site:" +
        // "<br>" +
        // "<input type=\"text\"  value= '" + contact.webSite + "'> " +
        // "</label> " +
        // "<br>" +
        // "email:" +
        // "<br>" +
        // "<input type=\"text\"  value= '" + contact.email + "'> " +
        // "</label> " +
        // "<br>" +
        // "Marital status:" +
        // "<br>" +
        // "<input name='input-label' id='maritalStatus'> " +
        // "</label> " +
        // "<br>" +
        // "Company:" +
        // "<br>" +
        // "<input type=\"text\"  value= '" + contact.company + "'> " +
        // "</label> " +
        // "<br>" +
        // "<label> " +
        // "<button type=\"submit\">Save</button> " +
        // "</label> " +


        "<label> " +
        "First name*:" +
        "<br>" +
        "<input name='input-label' id='firstName' required > " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Middle name:" +
        "<br>" +
        "<input name='input-label' id='middleName'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Last name*:" +
        "<br>" +
        "<input name='input-label'  id='lastName'  required > " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Gender:" +
        "<br>" +
        "<input name='input-label' id='gender' > " +
        "</label> " +
        "<br>" +
        "Citizenship:" +
        "<br>" +
        "<input name='input-label' id='citizenship' > " +
        "</label> " +
        "<br>" +
        "Web Site:" +
        "<br>" +
        "<input name='input-label' id='webSite' > " +
        "</label> " +
        "<br>" +
        "email:" +
        "<br>" +
        "<input name='input-label' id='email'> " +
        "</label> " +
        "<br>" +
        "Marital status:" +
        "<br>" +
        "<input name='input-label' id='maritalStatus'> " +
        "</label> " +
        "<br>" +
        "Company:" +
        "<br>" +
        "<input name='input-label' id='company'  > " +
        "</label> " +
        "</form>";

    "</form>";


    document.getElementById("inputs").innerHTML = textHTML;
}

function cancelButton() {
    history.back();
}

async function saveButton() {
    const firstName = document.getElementById('firstName').textContent;
    const middleName = document.getElementById('middleName').textContent;
    const lastName = document.getElementById('lastName').textContent;
    const gender = document.getElementById('gender').textContent;
    const citizenship = document.getElementById('citizenship').textContent;
    const webSite = document.getElementById('webSite').textContent;
    const email = document.getElementById('email').textContent;
    const maritalStatus = document.getElementById('maritalStatus').textContent;
    const company = document.getElementById('company').textContent;

    var data = {firstName, middleName, lastName, gender, citizenship, webSite, email, maritalStatus, company};

    const response = await fetch("?command=createContact", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(async response => {
            const json = await response.json();
            console.log(json);
            window.location.replace('index.html');
        }
    )
        .catch(function (error) {
            console.log(error);
        });

}