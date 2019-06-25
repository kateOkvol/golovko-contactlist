var request = new XMLHttpRequest();


// var response = '[' +
//     '{' +
//     '    "id":1,' +
//     '     "fullName":"Jessica Jones",' +
//     '     "birthDate":null,' +
//     '     "address":"New York",' +
//     '     "company":"Alias private investigations"' +
//     '},' +
//     '{' +
//     '     "id":2,' +
//     '     "fullName":"John Wick",' +
//     '     "birthDate":null,' +
//     '     "address":"USA",' +
//     '     "company":"Russian Mafia"' +
//     '},' +
//     '{' +
//     '    "id":3,' +
//     '    "fullName":"Steve Joseph Rogers",' +
//     '    "birthDate":"1918-07-04",' +
//     '    "address":"USA, New York",' +
//     '    "company":"The Avengers"' +
//     '}' +
//     ']';


function getContacts() {
    addButtons();
    request.open("GET", "/contactsList/application?command=mainContacts", true);
    request.onreadystatechange = createTable;
}

function addButtons() {
    var buttonHTML = "<form>";
    buttonHTML += "<button type='submit' id='0' formaction='contact-editor.html'>Create</button>";
    buttonHTML += "<button type='submit' id='2'>Delete</button>";
    buttonHTML += "</form>";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

function createTable() {
    if (request.readyState === 4 && request.status === 200) {
        var contactsList = JSON.parse(request);

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

        document.getElementById("contact-table").innerHTML = tableHTML;
    } else{
        console.log("cant find contacts")
    }
}





