function showAttachTable(contactId) {
    addAttachButtons();
    createAttachTable(contactId);
}

function phonesTableHTML() {
    var tableHTML = "<script type='text/html-template' id='template-attach-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>File name</th><th>Date of download</th><th>Note</th>";
    tableHTML += "<tbody id='attach-table-body'>";
    tableHTML += "</tr>";
    tableHTML += "{{#.}}";
    tableHTML += "<tr>";
    tableHTML += "<td><input type='checkbox' name='delete' id='attach{{id}}'></td>";
    tableHTML += "<td><a href='' id='path{{id}}' onclick='loadPopupPhones(event, id)'>{{path}}</a></td> " +
        "<td id='date{{id}}'> {{date}}</td>" +
        "<td id='note{{id}}'> {{note}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</tbody>";
    tableHTML += "</script>";

    document.getElementById("attach-table").innerHTML = tableHTML;
}

function createAttachTable(contactId) {

    return fetch("application?command=mainAttachment", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({"contactId": contactId})
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
            const template = document.getElementById("template-attach-table").innerHTML;
            console.log(template);
            document.getElementById("attach-table").innerHTML = Mustache.to_html(template, phonesList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addAttachButtons() {
    const url = "application?command=deleteAttachment";
    const tableBodyId = "attach-table-body";

    let buttonHTML = "<button type='submit' id='create' onclick='loadPopupPhones(event, 0)'>Create</button>";
    buttonHTML += "<button type='submit' id='delete' " +
        "onclick='deleteManager(\"" + tableBodyId + "\",\"" + url + "\")'>" +
        "Delete" +
        "</button>";
    document.getElementById("phones-buttons").innerHTML = buttonHTML;
}
