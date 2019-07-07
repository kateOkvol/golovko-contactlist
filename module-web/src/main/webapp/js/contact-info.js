var contactId;

function loadEditor(event, id) {
    event.preventDefault();
    document.getElementById("main-contact").style.display = 'none';
    document.getElementById("contact-editor").style.display = 'block';
    checkId(id);
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
    var buttonHTML = "<button type='submit' id= 'save-contact' onclick='contactSave()'>" +
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

function contactSave() {
    const id = contactId;
    const firstName = document.getElementById('firstName').value;
    const middleName = document.getElementById('middleName').value;
    const lastName = document.getElementById('lastName').value;
    const birthDate = document.getElementById('birthDate').value;
    const citizenship = document.getElementById('citizenship').value;
    const webSite = document.getElementById('webSite').value;
    const email = document.getElementById('email').value;
    const company = document.getElementById('company').value;

    const genderElement = document.getElementById('gender');
    const gender = genderElement.options[genderElement.selectedIndex].value;
    const maritalStatusElement = document.getElementById('maritalStatus');
    const maritalStatus = maritalStatusElement.options[maritalStatusElement.selectedIndex].value;

    const country = document.getElementById('country').value;
    const city = document.getElementById('city').value;
    const street = document.getElementById('street').value;
    const house = document.getElementById('house').value;
    const flat = document.getElementById('flat').value;
    const zipCode = document.getElementById('zipCode').value;

    var contactData;
    var url;
    if (contactId === 0) {
        contactData = {
            firstName, middleName, lastName, birthDate, gender, citizenship, webSite, email, maritalStatus, company,
            country, city, street, house, flat, zipCode
        };
        url = 'application?command=createContact';
    } else {
        contactData = {
            id, firstName, middleName, lastName, birthDate, gender, citizenship, webSite, email, maritalStatus, company,
            country, city, street, house, flat, zipCode
        };
        url = 'application?command=updateContact';
    }

    buttonSave(url, contactData);
}

async function buttonSave(url, data) {
    let returnData = await new Promise((resolve, reject) => {
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        }
    );
    console.log(returnData);
    manageScripts();
    noInputValues();
}

// var data = {id, firstName, middleName, lastName, gender, citizenship, webSite, email, maritalStatus, company};
// const contactForm = document.forms.namedItem('content');
// console.log(contactForm);
// const formData = new FormData(contactForm);
// console.log(formData);
// console.log(formData);