function deleteManager(tableBodyId, url) {
    if (confirm("Are you sure?")) {
        const id = getCheckbox(tableBodyId);
        if (id.length > 0) {
            deleteElementByURL(url, id);
        }
        if (url === 'deleteContacts') {
            contactId = null;
        }
    }
}

function getCheckbox(tableBodyId) {
    let selectedId = [];
    const tableBody = document.getElementById(tableBodyId);
    const elements = tableBody.getElementsByTagName('input');

    for (let i = elements.length - 1; i >= 0; i--) {
        if (elements[i].checked) {
            const newId = elements[i].id.replace(/\D+/g, "");
            const typeId = elements[i].id.replace(/[^a-z]/g, '');
            if (typeId !== 'new') {
                selectedId.push(newId)
            } else {
                deleteFromPostArray(newId, tableBodyId);
            }
            tableBody.deleteRow(i);
        }
    }
    return selectedId;
}

function deleteFromPostArray(id, tableBodyId) {
    if (tableBodyId === 'phone-table-body') {
        createPhonesArray.splice(-id, 1);
    }
    if (tableBodyId === 'attach-table-body') {
        createAttachArray.splice(-id, 1);
    }
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