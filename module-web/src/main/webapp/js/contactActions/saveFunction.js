function buttonSave() {
    let personalData = new FormData(document.forms.contactInputs);
    let addressData = new FormData(document.forms.addressInputs);
    var contactData = createPostContactData(personalData, addressData);
    var url;

    console.log(JSON.stringify(contactData));

    if (contactId === 0) {
        let promise = contactCreate('application?command=createContact', contactData);
        promise.then(result => {
            let id = JSON.parse(result);
            createPhone(id);
        })
    } else {
        contactSave('application?command=updateContact', contactData);
    }

    manageScripts();
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
        return fetch('application?command=createPhone', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(innerArray)
        })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(newPhone);
}

function updatePhone() {
    let newPhone = new Promise((resolve, reject) => {
        return fetch('application?command=updatePhone', {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updateArray)
        })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    console.log(newPhone);
}

function manageSave(responseId) {
    if (createArray.length !== 0) {
        createPhone(responseId);
    }

    if (updateArray.length !== 0) {
        updatePhone();
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