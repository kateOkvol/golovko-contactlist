function deleteManager(tableBodyId, url) {
    if (confirm("Are you sure?")) {
        var id = getCheckbox(tableBodyId);
        deleteElementByURL(url, id);
        contactId = null;
    }
}

function getCheckbox(tableBodyId) {
    let selectedId = [];
    const tableBody = document.getElementById(tableBodyId);
    const elements = tableBody.getElementsByTagName('input');

    for (var i = elements.length - 1; i >= 0; i--) {
        if (elements[i].checked) {
            const newId =elements[i].id.replace(/\D+/g, "");
            console.log(newId);
            selectedId.push(newId);
            tableBody.deleteRow(i);
        }
    }

    return selectedId;
}

async function deleteElementByURL(url, id) {
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