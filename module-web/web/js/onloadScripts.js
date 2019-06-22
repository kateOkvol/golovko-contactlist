var request = new XMLHttpRequest();

// var response =  [
//     {
//         "id":1,
//         "fullName":"Jessica Jones",
//         "birthDate":null,
//         "address":"New York",
//         "company":"Alias private investigations"
//     },
//     {
//         "id":2,
//         "fullName":"John Wick",
//         "birthDate":null,
//         "address":"USA",
//         "company":"Russian Mafia"
//     }
// ];


function showContacts() {
    // createTable();

    request.open("GET", "/contactsList/getAll", true);
    request.onreadystatechange = createTable;
    request.send(null);


}

function createTable() {
   if (request.readyState === 4 && request.status === 200) {
        var contactsList = JSON.parse(response);

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





