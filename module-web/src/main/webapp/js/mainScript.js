var request = new XMLHttpRequest();

function getContacts() {
    addButtons();
    request.open("POST", "?command=mainContacts", true);
    console.log("method is open");
    request.onreadystatechange = createTable;
    console.log("on ready state change");
    request.send(null);
    //createTable();
}

function addButtons() {
    var buttonHTML = "<form>";
    buttonHTML += "<button type='submit' id='0' formaction='html/contact-editor.html'>Create</button>";
    buttonHTML += "<button type='submit' id='delete'>Delete</button>";
    buttonHTML += "</form>";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

function createTable() {
    console.log("state "+ request.readyState + " status " + request.status);
    //if (request.readyState === 4 /*&& request.status === 200*/) {
        var contactsList = JSON.parse(request);

        console.log("parse is ok");

        var tableHTML = "<table id=\"contact-table\">";
        tableHTML += "<tr>";
        tableHTML += "<th>&#10004</th> <th>Full name</th> <th>Birth date</th> <th>Address</th> <th>Company</th>";
        tableHTML += "</tr>";

        for (var i = 0; i < contactsList.length; i++) {
            tableHTML += "<tr>";
            tableHTML += "<td><input type='checkbox' name='delete'" + i + " id=" + i + "></td>";
            tableHTML += "<td> <a href='../html/contact-editor.html'>" + contactsList[i].fullName + "</a> </td> " +
                "<td>" + contactsList[i].birthDate + "</td> " +
                "<td>" + contactsList[i].address + "</td> " +
                "<td>" + contactsList[i].company + "</td>";
            tableHTML += "</tr>";
        }
        tableHTML += "</table>";

        console.log("searching for element");
        document.getElementById("contact-table").innerHTML = tableHTML;
 //   } else {
     //   console.log("cant find contacts")
   // }
}





