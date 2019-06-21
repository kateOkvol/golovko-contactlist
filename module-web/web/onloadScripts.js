var request = new XMLHttpRequest();

function showContacts() {
    request.open("GET", "/contactsList?command=getAll", true);
    request.onreadystatechange = createTable;
    request.send(null);
}

function createTable() {
    console.log("start");
    var tableHTML = "<style type=\"text/css\"> TABLE {" +
                    " border-collapse: collapse; " +
                    "border: 1px solid black;} " +
                    "TD, TH {border: 1px solid black;}" +
                    "</style>";

    tableHTML += "<table id=\"contact-table\">";
    tableHTML += "<tr>";
    tableHTML += "<td>Full name</td> <td>Birth date</td> <td>Address</td> <td>Company</td>\n";
    tableHTML += "</tr>";
    tableHTML += "</table>";

    document.getElementById("contact-table").innerHTML = tableHTML;
}





