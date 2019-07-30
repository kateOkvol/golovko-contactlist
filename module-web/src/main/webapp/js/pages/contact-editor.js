let contactId;

function loadEditor(event, id) {
    event.preventDefault();
    document.getElementById('main-contact').style.display = 'none';
    document.getElementById('contact-editor').style.display = 'block';
    document.getElementById('h1').innerHTML = 'Contact Editor';
    checkId(id);
    showPhonesTable(contactId);
    showButtons();
}

function checkId(id) {
    contactId = id;
    if (id !== 0) {
        fillFields(id);
    } else {
        showAttaches(null);
        showContactInputs(null, 'contactInputs', 'content');
        showAddressInputs(null, 'addressInputs', 'addressContent');
    }
}

function showButtons() {
    let buttonHTML = "<button type='submit' id= 'save-contact' onclick='buttonSave()'>" +
        "Save" +
        "</button> " +
        "<button type='reset' id= 'cancel-creator' onclick='cancelButton(\"contact-editor\")'>" +
        "Cancel" +
        "</button> ";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

async function fillFields(id) {
    const contactId = {id};
    console.log(contactId);
    const options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contactId)
    };
    console.log(options);

    let promise = await new Promise((resolve, reject) => {
        return fetch("application?getContactById", options)
            .then(response => {
                return resolve(response.json());
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    showAttaches(promise);
    showContactInputs(promise, 'contactInputs', 'content');
    showAddressInputs(promise, 'addressInputs', 'addressContent');
}


function inputValues(info, promise) {
    if (promise == null || promise[info] == null || promise[info] === '') {
        document.getElementById(info).placeholder = 'fill the field';
    } else document.getElementById(info).value = promise[info];
}

function cancelButton(element) {
    if (confirm("Are you sure? You lose all the changes.")) {
        manageScripts(element);
    }
}