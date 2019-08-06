let phoneId;
let createPhonesArray = [];
let updatePhonesArray = [];
let inputPhonesForm;

function loadPopupPhones(event, id) {
    event.preventDefault();
    document.getElementById('attach-buttons').style.display = 'none';
    checkPhoneId(id);
}

function checkPhoneId(id) {
    if (id === 0) {
        phoneId = 0;
        showPhoneInputs(null);
    } else {
        phoneId = id.replace(/\D+/g, "");
        fillPhoneInputs(phoneId);
    }
}

function showPhoneInputs(promise) {
    phonePopUpWindow();
    inputValues('phone', promise);
    inputValues('operatorCode', promise);
    inputValues('countryCode', promise);
    inputValues('type', promise);
    inputValues('note', promise);
}

function phonePopUpWindow() {
    const textHTML = "<fieldset id='phones-fieldset'>" +
        "<form id='phonePopupForm' enctype='multipart/form-data'>" +
        "<label> " +
        "<br>" +
        "Phone number*:" +
        "<br>" +
        "<input name='phone' id='phone' type='tel' required='required'> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Operator code:" +
        "<br>" +
        "<input name='operatorCode' id='operatorCode'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Country code:" +
        "<br>" +
        "<input name='countryCode' id='countryCode'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Type:" +
        "<br>" +
        "<select  name='type' id='type'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='mobile'>mobile</option>" +
        "<option value='home'>home</option>" +
        "</select> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Note:" +
        "<br>" +
        "<input name='note' id='note'> " +
        "</label> " +
        "<div id='phone-popup-buttons'></div>" +
        "</form>" +
        "</fieldset>";
    const element = document.getElementById('phones-window');
    element.innerHTML = textHTML;
    element.style.display = 'block';
    addPhonePopupButtons();
}

function addPhonePopupButtons() {
    const textHTML = "<button id='phoneOk' onclick='phonesSave()' type='submit'>Ok</button>" +
        "<button id='phoneCancel' type='reset'>Cancel</button>";
    document.getElementById('phone-popup-buttons').innerHTML = textHTML;
    document.getElementById('phoneCancel').addEventListener('click', cancelPhonesPop, false);
}

function cancelPhonesPop() {
    document.getElementById('attach-buttons').style.display = 'block';
    cancelButton("phones-window");
}

async function fillPhoneInputs(id) {
    const numberId = {id};
    const options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(numberId)
    };

    let promise = await new Promise((resolve, reject) => {
        return fetch("application?getPhoneById", options)
            .then(response => {
                return resolve(response.json());
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    showPhoneInputs(promise);
}


function phonesSave() {
    validPhone(document.forms.phonePopupForm.phone);
    inputPhonesForm = new FormData(document.forms.phonePopupForm);
    if (phoneId === 0) {
        createPhoneRow();
    } else {
        editPhoneRow();
    }
    document.getElementById('attach-buttons').style.display = 'block';
    manageScripts("phones-window");
}

function createPhoneRow() {
    let elementTable = getPhoneTbody();
    let innerArray = {};
    inputPhonesForm.forEach(function (value, key) {
        innerArray[key] = value;
        if (contactId !== 0) {
            innerArray['contactId'] = contactId;
        }
    });
    createPhonesArray.push(innerArray);

    let row = elementTable.insertRow(0);
    let countryCode = Number(innerArray['countryCode']);
    let operatorCode = Number(innerArray['operatorCode']);
    let phone = Number(innerArray['phone']);
    let type = innerArray['type'];
    let note = innerArray['note'];

    row.insertCell(0).innerHTML = "<input type='checkbox' id='new" + (createPhonesArray.length-1)+"' name='delete'>";
    row.insertCell(1).innerHTML =
        "<a href='' id='phone0' onclick='loadPopupPhones(event, id)'>" +
        inputNotNull(countryCode, operatorCode, phone) +
        "</a>";
    row.insertCell(2).innerHTML = type;
    row.insertCell(3).innerHTML = note;
    return innerArray;
}

function editPhoneRow() {
    inputPhonesForm.append('contactId', contactId);
    inputPhonesForm.append('id', phoneId);
    let innerArray = {};
    inputPhonesForm.forEach(function (value, key) {
        innerArray[key] = value;
    });
    updatePhonesArray.push(innerArray);

    let countryCode = Number(innerArray['countryCode']);
    let operatorCode = Number(innerArray['operatorCode']);
    let phone = Number(innerArray['phone']);
    let type = innerArray['type'];
    let note = innerArray['note'];

    document.getElementById('number' + phoneId).innerHTML = inputNotNull(countryCode, operatorCode, phone);
    document.getElementById('type' + phoneId).innerHTML = type;
    document.getElementById('note' + phoneId).innerHTML = note;

    return innerArray;
}

function inputNotNull(countryCode, operatorCode, phone) {
    let countryCodeInput = (countryCode !== 0) ? countryCode : '';
    let operatorCodeInput = (operatorCode !== 0) ? operatorCode : '';
    let phoneInput = (phone !== 0) ? phone : '';
    return countryCodeInput + " " + operatorCodeInput + " " + phoneInput;
}

function validPhone(phone) {
    if (phone.value===''){
        throw '';
    }
}

function getPhoneTbody() {
    if(document.getElementById('phone-table-body')===null){
        let tbody = document.createElement('tbody');
        tbody.id = 'phone-table-body';
        document.getElementById('phones-table').appendChild(tbody);
    }
    return  document.getElementById('phone-table-body');
}