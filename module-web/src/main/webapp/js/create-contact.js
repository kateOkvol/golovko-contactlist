function createCreator() {
    showButtons();
    showInputs();
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

function showInputs() {
    var textHTML = "<form> " +
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

  //И З М Е Н И Т Ь    У Р Л   П О С Л Е   П О Д К Р У Т К И   М У С Т А Ш
    const response = await fetch("?command=createContact", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    const json = await response.json();
    console.log(json);
    window.location.replace('index.html');
}