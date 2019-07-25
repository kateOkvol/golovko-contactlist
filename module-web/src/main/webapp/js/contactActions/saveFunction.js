function buttonSave() {
    let personalData = new FormData(document.forms.contactInputs);
    let addressData = new FormData(document.forms.addressInputs);
    if (ava != null) {
        personalData.append('avatar', ava.name);
    }
    let contactData = createPostContactData(personalData, addressData);

    if (contactId !== 0) {
        contactSave('application?updateContact', contactData);
    } else {
        let promise = contactCreate('application?createContact', contactData);
        promise.then(result => {
            let id = JSON.parse(result);
            manageSave(id);
        })
    }
    manageScripts('contact-editor');
}

function contactCreate(url, data) {
    return new Promise((resolve, reject) => {
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'application/json'
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
    inputForm.append('contactId', id);
    let innerArray = {};
    inputForm.forEach(function (value, key) {
        innerArray[key] = value;
    });

    let newPhone = new Promise((resolve, reject) => {
        return fetch('application?createPhone', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(innerArray)
        })
            .then(() => {
                inputForm.length = 0;
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
            body: JSON.stringify(updateArray)
        })
            .then(() => {
                updateArray.length = 0;
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
                    attachMetaInfFetch(i);
                })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        });
    }
}

function attachMetaInfFetch(index) {
    new Promise((resolve, reject) => {
        return fetch('application?createAttach', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(createAttachArray[index])
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
    if (createArray.length !== 0) {
        createPhone(responseId);
    }
    if (updateArray.length !== 0) {
        updatePhone();
    }
    if (createAttachArray.length !== 0) {
        createAttachFetch(responseId);
    }
    if (updateAttachArray.length !== 0) {
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