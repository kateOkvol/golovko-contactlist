const url = "application?command=deletePhone";
const tableBodyId = "phone-table-body";

function showPhonesTable(contactId) {
    createPhonesTable(contactId);
    addPhonesButtons();
}

function phonesTableHTML() {
    var tableHTML = "<script type='text/html-template' id='template-phones-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>Full number</th><th>Phone number type</th><th>Note</th>";
    tableHTML += "</tr>";
    tableHTML += "<tbody id='phone-table-body'>";
    tableHTML += "{{#.}}";
    tableHTML += "<tr>";
    tableHTML += "<td><input type='checkbox' name='delete'  id='phone{{number_id}}'></td>";
    tableHTML += "<td> {{type}}</td>" +
        "<td>{{fullNumber}}</td> " +
        "<td> {{note}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</tbody>";
    tableHTML += "</script>";

    document.getElementById("phones-table").innerHTML = tableHTML;
}

function createPhonesTable(contactId) {

    return fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(contactId)
        }
    )
        .then(response => {
            return response.json().catch(error => {
                return Promise.reject(new Error('Invalid JSON: ' + error.message));
            });
        })
        .then(response => {
            const phonesList = response;
            console.log(phonesList);
            phonesTableHTML();
            const template = document.getElementById("template-phones-table").innerHTML;
            console.log(template);
            document.getElementById("phones-table").innerHTML = Mustache.to_html(template, phonesList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addPhonesButtons() {
    var buttonHTML = "<button type='submit' id='create' onclick=''>Create</button>";
    buttonHTML += "<button type='submit' id='delete' onclick='deleteManager(\"" + tableBodyId + "\",\"" + url + ")'>Delete</button>";
    document.getElementById("phones-buttons").innerHTML = buttonHTML;
}