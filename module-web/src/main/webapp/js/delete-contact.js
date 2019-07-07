var id = [];

function deleteManager() {
    getCheckbox();
    deleteContact();
    id = [];
}

function getCheckbox() {
    console.log('the method is run');
    const elements = document.getElementsByName('delete');
    console.log(elements);
    const tableBody = document.getElementById("table-body");
    console.log(tableBody);

    for (var i = elements.length - 1; i >= 0; i--) {
        if (elements[i].checked) {
            id.push(elements[i].id);
            tableBody.deleteRow(i);
        }
    }
    console.log(tableBody);

    // for(var i = 0; i <checkedElements.length; i++){
    //     tableBody.deleteRow(i);
    // }
}

async function deleteContact() {

    console.log(id);
    return await fetch("application?command=deleteContact", {
        method: 'POST',   //delete?
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(id)
    }).catch(function (error) {
        console.log(error);
    })
}