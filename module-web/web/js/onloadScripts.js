var request = new XMLHttpRequest();

function showContacts() {
    request.open("GET", "/contactsList/getAll", true);
    request.onreadystatechange = createTable;
    request.send(null);
}

function createTable() {
    if (request.readyState === 4 && request.status === 200) {
        var contactsList = JSON.parse(request.response);

        var tableHTML = "<table id=\"contact-table\">";
        tableHTML += "<tr>";
        tableHTML += "<td>Full name</td> <td>Birth date</td> <td>Address</td> <td>Company</td>";
        tableHTML += "</tr>";
        tableHTML += "</table>";

        for (var i = 0; i < contactsList.length; i++) {
            tableHTML += "<tr>";
            tableHTML += "<td>" + contactsList[i].fullName + "</td> " +
                "<td>" + contactsList[i].birthDate + "</td> " +
                "<td>" + contactsList[i].address + "</td> " +
                "<td>" + contactsList[i].company + "</td>";
            tableHTML += "</tr>";
        }
        document.getElementById("contact-table").innerHTML = tableHTML;
    } else {
        console.log("cant create table");
    }
}





