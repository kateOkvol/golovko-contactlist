var contactsList;

function getContacts() {
    addButtons();
    createTable();
}

function addButtons() {
    var buttonHTML = "<form>";
    buttonHTML += "<button type='submit' id='create' formaction='html/contact-editor.html'>Create</button>";
    buttonHTML += "<button type='submit' id='delete' formaction='delete-contact.js' onclick='getCheckbox()'>Delete</button>";
    buttonHTML += "</form>";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

async function createTable() {
    return fetch("application?command=mainContacts"
    )
        .then(response => {
            return response.json();
        })
        .then(async response => {
            contactsList = await response;
            tableText();
        });
}

function tableText() {
    var tableHTML = "<table id=\"contact-table\">";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th> <th>Full name</th> <th>Birth date</th> <th>Address</th> <th>Company</th>";
    tableHTML += "</tr>";

    for (var i = 0; i < contactsList.length; i++) {
        tableHTML += "<tr>";
        tableHTML += "<td><input type='checkbox' name='delete'  id='delete" + i + "'></td>";
        tableHTML += "<td> <a href='../html/contact-editor.html' id=''>" + contactsList[i].fullName + "</a> </td> " +
            "<td>" + contactsList[i].birthDate + "</td> " +
            "<td>" + contactsList[i].address + "</td> " +
            "<td>" + contactsList[i].company + "</td>";
        tableHTML += "</tr>";
    }
    tableHTML += "</table>";

    document.getElementById("contact-table1").innerHTML = tableHTML;
}







