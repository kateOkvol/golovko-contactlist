function showPhonesTable(contactId) {
    addPhonesButtons();
    if (contactId === 0) {
        document.getElementById("phones-table").innerHTML = "<tr><th>&#10004</th><th>Full number</th><th>Phone number type</th><th>Note</th></tr>";
    } else {
        createPhonesTable(contactId);
    }
}

function phonesTableHTML() {
    let tableHTML = "<script type='text/html-template' id='template-phones-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>Full number</th><th>Phone number type</th><th>Note</th>";
    tableHTML += "<tbody id='phone-table-body'> ";
    tableHTML += "{{#.}}";
    tableHTML += "</tr>";
    tableHTML += "<tr>";
    tableHTML += "<td><input type='checkbox' name='delete' id='phone{{number_id}}'></td>";
    tableHTML += "<td><a href='' id='number{{number_id}}' onclick='loadPopupPhones(event, id)'>{{fullNumber}}</a></td> " +
        "<td id='type{{number_id}}'> {{type}}</td>" +
        "<td id='note{{number_id}}'> {{note}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</tbody>";
    tableHTML += "</script>";

    document.getElementById("phones-table").innerHTML = tableHTML;
}

function createPhonesTable(contactId) {
    let url = "application?mainPhones";
    let options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({"contactId": contactId})
    };

    return fetch(url, options)
        .then(response => {
            return response.json()
        })
        .then(response => {
            const phonesList = response;
            console.log(phonesList);
            phonesTableHTML();
            const template = document.getElementById("template-phones-table").innerHTML;
            document.getElementById("phones-table").innerHTML = Mustache.to_html(template, phonesList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addPhonesButtons() {
    let buttonHTML = "<button type='submit' id='create' onclick='loadPopupPhones(event, 0)'>Create</button>";
    buttonHTML += "<button type='submit' id='delete' onclick='deletePhone()'>Delete</button>";
    document.getElementById("phones-buttons").innerHTML = buttonHTML;
}

function deletePhone() {
    const url = "application?deletePhone";
    const tableBodyId = "phone-table-body";
    deleteManager(tableBodyId, url);
}
