function buttonSave() {
    let personalData = new FormData(document.forms.contactInputs);
    let addressData = new FormData(document.forms.addressInputs);
    setAvaValue(contactId);
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

function uploadAvaFetch(file) {
    let form = new FormData();
    form.append('file', file);
    let promise = new Promise((resolve, reject) => {
        return fetch('application?uploadAva', {
            method: 'POST',
            body: form
        })
            .then(() => {
                ava = null;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            });
    });
    console.log(promise);
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
                phoneId = 0;
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
                phoneId = 0;
                updatePhonesArray.length = 0;
                inputPhonesForm.length = 0;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(updatedPhone);
}


function uploadAttachFetch(id) {
    for (let i = 0; i < inputAttachForm.length; i++) {
        let form = inputAttachForm[i];
        const file = form.get('file');
        const type = file.name.split(/\.(?=[^\.]+$)/)[1];
        const name = id[i].toString() + '.' + type;
        const newFile = new File([file], name);
        form = new FormData();
        form.append('file', newFile);

        new Promise((resolve, reject) => {
            return fetch('application?uploadAttach', {
                method: 'POST',
                body: form
            })
                .then(() => {
                    attachId = 0;
                    inputAttachForm.length = 0;
                })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        });
    }
}

function createAttachFetch(respId) {
    if (contactId === 0) {
        createAttachArray.forEach(function (element) {
            element['contactId'] = respId;
        });
    } else {
        contactId = 0;
    }
    return fetch('application?createAttach', {
        method: 'POST',
        headers: {
            'Accept-type': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(createAttachArray)
    })
        .then(response => {
            createAttachArray.length = 0;
            return response.json();
        }).then(result => {
            uploadAttachFetch(result);
        })
        .catch(function (error) {
            console.log(new Error(error.message));
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
                attachId = 0;
                contactId = 0;
                updateAttachArray.length = 0;
                inputAttachForm.length = 0;
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
        createAttachFetch(responseId);
    } else {
        contactId = 0;
    }
    if (updateAttachArray !== undefined && updateAttachArray.length > 0) {
        updateAttachFetch();
    }
    setAvaValue(responseId);
    if (ava !== '') {
        const newFile = new File([file], ava);
        uploadAvaFetch(newFile);
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

function setAvaValue(id) {
    if ((ava === '/resource/noAva.jpg') || (ava === "no-avatar\\noAva.jpg") || (ava === '')) {
        ava = '';
    } else {
        const type = ava.split(/\.(?=[^\.]+$)/)[1];
        if (id !== 0) {
            ava = id.toString() + '.' + type;
        } else {
            ava = '.' + type;
        }
    }
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
    const date = document.getElementById('birthDate');
    const mess = date.validationMessage;
    const now = new Date();
    if ((mess !== '') ||
        (new Date(date.value) > now)) {
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