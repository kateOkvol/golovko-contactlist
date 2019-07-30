let contactsList;

function loadMain() {
    event.preventDefault();
    addActionButtons();
    addNavigationButtons();
    createTable(1);
    pagination();
}


function addActionButtons() {
    const tableBodyId = "\"table-body\"";
    const url = "\"application?deleteContacts\"";
    const parameters = tableBodyId + ", " + url;
    let buttonHTML = "<button type='submit' id='create' onclick='manageScripts(\"main-contact\",\"contact-editor\")'>Create</button>";
    buttonHTML += "<button type='submit' id='delete' onclick='deleteManager(" + parameters + ")'>Delete</button>" +
        "<button type='submit' id='email-button' onclick='manageScripts(\"main-contact\",\"email-page\");'>Send email</button>" +
        "<button type='submit' id='search-button' onclick='manageScripts(\"main-contact\",\"search-page\");'>Search</button>";
    document.getElementById("main-buttons").innerHTML = buttonHTML;
}

function addNavigationButtons() {
    let textHTML = "<button id='back' disabled = disabled>&#11207</button>" +
        "<button id='forth'><label>&#11208</label></button>";
    document.getElementById('navigation').innerHTML = textHTML;
}

function createTable(page) {
    return fetch("application?mainContacts", {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(page)
        }
    )
        .then(response => {
            return response.json().catch(error => {
                return Promise.reject(new Error('Invalid JSON: ' + error.message));
            });
        })
        .then(response => {
            contactsList = response;
            tableText();
            let template = document.getElementById("template-table").innerHTML;
            console.log(template);
            document.getElementById("contact-table").innerHTML = Mustache.to_html(template, contactsList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function tableText() {
    let tableHTML = "<script type='text/html-template' id='template-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>Full name</th><th>Birth date</th><th>Address</th><th>Company</th>";
    tableHTML += "</tr>";
    tableHTML += "<tbody id='table-body'>";
    tableHTML += "{{#.}}";
    tableHTML += "<tr>";
    tableHTML += "<td><input type='checkbox' name='delete'  id='{{id}}'></td>";
    tableHTML += "<td><a href='' id={{id}} onclick='loadEditor(event, id)'>{{fullName}}</a></td> " +
        "<td> {{birthDate}}</td>" +
        "<td> {{address}}</td>" +
        "<td> {{company}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</tbody>";
    tableHTML += "</script>";

    document.getElementById("contact-table").innerHTML = tableHTML;
}

function pagination() {
    let page = 2;
    document.getElementById('forth').addEventListener('click', function () {
        createTable(page);
        page++;
        document.getElementById('back').disabled = page === 1;
    }, false);
    document.getElementById('back').addEventListener('click', function () {
        page--;
        createTable(page);
        document.getElementById('back').disabled = page === 1;
    }, false);

}