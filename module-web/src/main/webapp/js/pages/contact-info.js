var contactId;

function loadEditor(event, id) {
    event.preventDefault();
    document.getElementById("main-contact").style.display = 'none';
    document.getElementById("contact-editor").style.display = 'block';
    checkId(id);
    showPhonesTable(contactId);
    showButtons();
}

function checkId(id) {
    contactId = id;
    if (id !== 0) {
        fillInputs(id);
    } else {
        showContactInputs(null);
        showAddressInputs(null);
    }
}

function showButtons() {
    var buttonHTML = "<button type='submit' id= 'save-contact' onclick='contactSave(contactId)'>" +
        "Save" +
        "</button> " +
        "<button type='reset' id= 'cancel-creator' onclick='cancelButton()'>" +
        "Cancel" +
        "</button> ";
    document.getElementById("buttons").innerHTML = buttonHTML;
}

async function fillInputs(id) {
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
        return fetch("application?command=getContactById", options)
            .then(response => {
                return resolve(response.json());
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });

    showContactInputs(promise);
    showAddressInputs(promise);
}


function inputValues(info, promise) {
    document.getElementById(info).value = promise[info];
    if (promise[info] == null) {
        document.getElementById(info).placeholder = 'fill the field';
    }
}

function noInputValues() {
    const contactInfo = document.getElementsByName('input-label');

    contactInfo.forEach(function (input) {
        input.value = null;
        input.placeholder = 'fill the field'
    })
}

function cancelButton() {
    noInputValues();
    manageScripts();
}


// var data = {id, firstName, middleName, lastName, gender, citizenship, webSite, email, maritalStatus, company};
// const contactForm = document.forms.namedItem('content');
// console.log(contactForm);
// const formData = new FormData(contactForm);
// console.log(formData);
// console.log(formData);