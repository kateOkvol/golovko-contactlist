var contactsList;

function loadMain() {
    document.getElementById("contact-editor").style.display = 'none';
    addButtons();
    createTable();
}



function addButtons() {
    var buttonHTML = "<form>";
    buttonHTML += "<button type='submit' id='create' onclick='loadEditor(0)'>Create</button>";
    buttonHTML += "<button type='submit' id='delete' onclick='getCheckbox()'>Delete</button>";
    buttonHTML += "</form>";
    document.getElementById("main-buttons").innerHTML = buttonHTML;
}

async function createTable() {
    return fetch("application?command=mainContacts"
    )
        .then(response => {
            return response.json();
        })
        .then(async response => {
            contactsList = await response;
            console.log(contactsList);
            tableText();
            var template = document.getElementById("template-table").innerHTML;
            console.log(template);
            document.getElementById("contact-table").innerHTML = Mustache.to_html(template, contactsList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function tableText() {

    var tableHTML = "<table id='contact-table'>";
    tableHTML += "<script type='text/html-template' id='template-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>Full name</th><th>Birth date</th><th>Address</th><th>Company</th>";
    tableHTML += "</tr>";
    tableHTML += "{{#.}}";
    tableHTML += "<tr>";
    tableHTML += "<td><input type='checkbox' name='delete'  id='delete{{id}}'></td>";
    tableHTML += "<td><a href='' id={{id}} onclick='loadEditor(id)'>{{fullName}}</a></td> " +
        "<td> {{birthDate}}</td>" +
        "<td> {{address}}</td>" +
        "<td> {{company}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</script>";
    tableHTML += "</table>";

    document.getElementById("contact-table").innerHTML = tableHTML;
}







