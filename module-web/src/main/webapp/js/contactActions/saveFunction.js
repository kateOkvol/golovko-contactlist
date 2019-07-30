function buttonSave() {
    let personalData = new FormData(document.forms.contactInputs);
    let addressData = new FormData(document.forms.addressInputs);
    if (ava != null) {
        personalData.append('avatar', ava.name);
    }
    let contactData = createPostContactData(personalData, addressData);

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
                return resolve(response.json());
            })
                .catch(function (error) {
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
            }).then(() => {
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
                    inputAttachForm.shift();
                })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        });
    }
}

function attachMetaInfFetch(respId) {
    createAttachArray.forEach(function (element) {
        element['contactId'] = respId;
    });
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
                createAttachArray.shift();
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

function createPostContactData(personalData, addressData) {
    let contactData = {};
    if (contactId !== 0) {
        personalData.append('id', contactId);
    }
    personalData.forEach(function (value, key) {
        contactData[key] = value;
    });
    addressData.forEach(function (value, key) {
        contactData[key] = value;
    });

    return contactData;
}