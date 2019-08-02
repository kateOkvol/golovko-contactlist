function buttonSave() {
    let personalData = new FormData(document.forms.contactInputs);
    let addressData = new FormData(document.forms.addressInputs);
    if ((ava === '/resource/noAva.jpg')||(ava ==="no-avatar\\noAva.jpg")) {
        ava = '';
    }
    personalData.append('avatar', ava);
    let contactData = createPostContactData('', personalData, addressData);

    if (contactId !== 0) {
        contactSave('application?updateContact', contactData);
        manageScripts('contact-editor');
    } else {
        let promise = contactCreate('application?createContact', contactData);
        promise.then(result => {
            let id = JSON.parse(result);
            manageSave(id);
        });
        manageScripts('contact-editor');
    }
}

function contactCreate(url, data) {
    return new Promise((resolve, reject) => {
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'multipart/form-data'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    alert('Contact was created successfully');
                }
                return resolve(response.json());
            })
                .catch(function (error) {
                    alert('Error creating contact');
                    reject(new Error(error.message));
                })
        }
    );
}

function contactSave(url, data) {
    new Promise((resolve, reject) => {
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    alert('Contact was saved successfully');
                }
                manageSave(contactId);
            })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        }
    );
}

function createPhone(id) {
    createPhonesArray.forEach(function (element) {
        element['contactId'] = id;
    });
    let newPhone = new Promise((resolve, reject) => {
        return fetch('application?createPhone', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(createPhonesArray)
        })
            .then(() => {
                createPhonesArray.length = 0;
                inputPhonesForm.length = 0;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(newPhone);
}

function updatePhone() {
    let updatedPhone = new Promise((resolve, reject) => {
        return fetch('application?updatePhone', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatePhonesArray)
        })
            .then(() => {
                updatePhonesArray.length = 0;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(updatedPhone);
}


function createAttachFetch() {
    for (let i = 0; i < inputAttachForm.length; i++) {
        new Promise((resolve, reject) => {
            return fetch('application?uploadAttach', {
                method: 'POST',
                body: inputAttachForm[i]
            })
                .then(() => {
                    inputAttachForm.length = 0;
                })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        });
    }
}

function attachMetaInfFetch(respId) {
    if(contactId===0) {
        createAttachArray.forEach(function (element) {
            element['contactId'] = respId;
        });
    }
    new Promise((resolve, reject) => {
        return fetch('application?createAttach', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(createAttachArray)
        })
            .then(() => {
                createAttachArray.length = 0;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
}

function updateAttachFetch() {
    let updatedAttach = new Promise((resolve, reject) => {
        return fetch('application?updateAttach', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updateAttachArray)
        })
            .then(() => {
                inputAttachForm.length = 0;
                updateAttachArray.length = 0;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(updatedAttach);
}

function manageSave(responseId) {
    if (createPhonesArray !== undefined && createPhonesArray.length > 0) {
        createPhone(responseId);
    }
    if (updatePhonesArray !== undefined && updatePhonesArray.length > 0) {
        updatePhone();
    }
    if (createAttachArray !== undefined && createAttachArray.length > 0) {
        createAttachFetch();
        attachMetaInfFetch(responseId);
    }
    if (updateAttachArray !== undefined && updateAttachArray.length > 0) {
        updateAttachFetch();
    }
    if (ava != null) {
        uploadAvaFetch(ava);
    }
}

function createPostContactData(page, personalData, addressData) {
    let contactData = {};
    if (contactId !== 0) {
        personalData.append('id', contactId);
    }
    personalData.forEach(function (value, key) {
        if (page !== 'search') {
            valid(key, value);
        }
        if (value === "") {
            contactData[key] = null;
        } else {
            contactData[key] = value;
        }
    });
    addressData.forEach(function (value, key) {
        if (value === "") {
            contactData[key] = null;
        } else {
            contactData[key] = value;
        }
    });

    return contactData;
}

function valid(key, value) {
    if ((key === 'firstName' || key === 'lastName')
        && (value === '')) {
        alert("fill the " + key + " filed ");
        throw '';
    }
    if (key === 'birthDate') {
        validDate(value);
    }
    if ((key === 'email') && (value !== '')) {
        validEmail(value);
    }
}

function validDate() {
    const mess = document.getElementById('birthDate').validationMessage;
    if (mess !== '') {
        alert("Date is not valid");
        throw '';
    }
}

function validEmail(value) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(String(value).toLowerCase())) {
        alert("Email is not valid");
        throw '';
    }
}