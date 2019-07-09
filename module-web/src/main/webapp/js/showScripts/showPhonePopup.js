var phoneId;

function loadPopupPhones(event, id) {
    event.preventDefault();
    checkPhoneId(id);
}

function checkPhoneId(id) {
    phoneId = id.replace(/\D+/g, "");
    if (phoneId !== 0) {
        fillPhoneInputs(phoneId);
    } else {
        phonePopUpWindow();
    }
}

function showPhoneInputs(promise) {
    phonePopUpWindow();
    if (promise == null) {
        noInputValues('phonesPopUp');
    } else {
        inputValues('number', promise);
        inputValues('operatorCode', promise);
        inputValues('countryCode', promise);
        inputValues('type', promise);
        inputValues('note', promise);
    }
}

function phonePopUpWindow() {
    var textHTML = "<fieldset id='phones-fieldset'>" +
        "<label> " +
        "<br>" +
        "Phone number*:" +
        "<br>" +
        "<input name='phonesPopUp' id='number' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Operator code:" +
        "<br>" +
        "<input name='phonesPopUp' id='operatorCode'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Country code:" +
        "<br>" +
        "<input name='phonesPopUp'  id='countryCode'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Type:" +
        "<br>" +
        "<select  id='type'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='mobile'>mobile</option>" +
        "<option value='home'>home</option>" +
        "</select> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Note:" +
        "<br>" +
        "<input name='phonesPopUp' id='note'> " +
        "</label> " +
        "<label> " +
        "<div id='phone-popup-buttons'></div>"+
        "</fieldset>";
    const element = document.getElementById('phones-window');
    element.innerHTML = textHTML;
    element.style.display = 'block';
    addPhonePopupButtons();
}

function addPhonePopupButtons(){
    var textHTML = "<button type='button' script='addNumberToTable(phoneId)'>Ok</button>" +
        "<button type='reset' onclick='cancelButton(\"phonesPopUp\")'>Cancel</button>";
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
        return fetch("application?command=getPhoneById", options)
            .then(response => {
                return resolve(response.json());
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });

    console.log(promise.number);

    showPhoneInputs(promise);
}