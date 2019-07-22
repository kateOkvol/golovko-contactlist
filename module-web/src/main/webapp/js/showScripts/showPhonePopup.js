var phoneId;
var createArray = [];
var updateArray = [];
var inputForm;

function loadPopupPhones(event, id) {
    event.preventDefault();
    checkPhoneId(id);
}

function checkPhoneId(id) {
    if (id === 0) {
        phoneId = 0;
        phonePopUpWindow();
    } else {
        phoneId = id.replace(/\D+/g, "");
        fillPhoneInputs(phoneId);
    }

}

function showPhoneInputs(promise) {
    phonePopUpWindow();
    if (promise !== null) {
        inputValues('phone', promise);
        inputValues('operatorCode', promise);
        inputValues('countryCode', promise);
        inputValues('type', promise);
        inputValues('note', promise);
    }
}

function phonePopUpWindow() {
    var textHTML = "<fieldset id='phones-fieldset'>" +
        "<form id='phonePopupForm' enctype='multipart/form-data'>" +
        "<label> " +
        "<br>" +
        "Phone number*:" +
        "<br>" +
        "<input name='phone' id='phone' required> " +
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
        "<input name='countryCode'  id='countryCode'> " +
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
        "</form>" +
        "<div id='phone-popup-buttons'></div>" +
        "</fieldset>";
    const element = document.getElementById('phones-window');
    element.innerHTML = textHTML;
    element.style.display = 'block';
    addPhonePopupButtons();
}

function addPhonePopupButtons() {
    var textHTML = "<button id='phoneOk' type='submit' onclick='phonesSave()'>Ok</button>" +
        "<button type='reset' onclick='cancelButton()'>Cancel</button>";
    document.getElementById('phone-popup-buttons').innerHTML = textHTML;
}

async function fillPhoneInputs(id) {
    const numberId = {id};
    console.log(numberId);
    const options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(numberId)
    };
    console.log(options);

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
    let elementTable = document.getElementById('phones-table');

    let innerArray = {};
    inputForm = new FormData(document.forms.phonePopupForm);

    if (phoneId === 0) {
        inputForm.forEach(function (value, key) {
            innerArray[key] = value;
        });
        createArray.push(innerArray);

        let row = elementTable.insertRow(1);
        let countryCode = Number(innerArray['countryCode']);
        let operatorCode = Number(innerArray['operatorCode']);
        let phone = Number(innerArray['phone']);
        let type = innerArray['type'];
        let note = innerArray['note'];

        row.insertCell(0).innerHTML = "<input type='checkbox' name='delete'>";
        row.insertCell(1).innerHTML =
            "<a href='' id='phone0' onclick='loadPopupPhones(event, id)'>" +
            inputNotNull(countryCode, operatorCode, phone) +
            "</a>";
        row.insertCell(2).innerHTML = type;
        row.insertCell(3).innerHTML = note;
    } else {
        inputForm.append('contactId', contactId);
        inputForm.append('id', phoneId);
        inputForm.forEach(function (value, key) {
            innerArray[key] = value;
        });
        updateArray.push(innerArray);

        let countryCode = Number(innerArray['countryCode']);
        let operatorCode = Number(innerArray['operatorCode']);
        let phone = Number(innerArray['phone']);
        let type = innerArray['type'];
        let note = innerArray['note'];

        document.getElementById('number' + phoneId).innerHTML = inputNotNull(countryCode, operatorCode, phone);
        document.getElementById('type' + phoneId).innerHTML = type;
        document.getElementById('note' + phoneId).innerHTML = note;
    }
    manageScripts();
}

function inputNotNull(countryCode, operatorCode, phone) {
    let countryCodeInput = (countryCode !== 0) ? countryCode : '';
    let operatorCodeInput = (operatorCode !== 0) ? operatorCode : '';
    let phoneInput = (phone !== 0) ? phone : '';
    return countryCodeInput + " " + operatorCodeInput + " " + phoneInput;
}


