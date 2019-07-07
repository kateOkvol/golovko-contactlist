function deleteManager(tableBodyId, url) {
    if (confirm("Are you sure?")) {
        var id = getCheckbox(tableBodyId);
        deleteContacts(url, id);
        contactId = null;
    }
}

function getCheckbox(tableBodyId) {
    var id = [];
    console.log('the method is run');
    const tableBody = document.getElementById(tableBodyId);
    const elements = tableBody.getElementsByTagName('input');
    console.log(elements);

    console.log(tableBody);

    for (var i = elements.length - 1; i >= 0; i--) {
        if (elements[i].checked) {
            const newId =elements[i].id.replace(/\D+/g, "");
            console.log(newId);
            id.push(newId);
            tableBody.deleteRow(i);
        }
    }
    console.log(tableBody);

    return id;
}

async function deleteContacts(url, id) {
    console.log(id);
    return await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(function (error) {
        console.log(error);
    })
}