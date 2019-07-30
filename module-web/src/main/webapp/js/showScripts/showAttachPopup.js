let attachId = 0;
let createAttachArray = [];
let updateAttachArray = [];
let inputAttachForm = [];

function loadAttachPopup(event) {
    event.preventDefault();
    getSelected();
}

function getSelected() {
    const element = document.querySelector(".messageCheckbox:checked");
    if (element === null) {
        alert("Choose any file to edit");
    } else {
        attachId = element.id.replace(/\D+/g, "");
        fillAttachInputs(attachId);
    }
}


function showAttachInputs(promise) {
    attachPopUpWindow(promise);
    if (promise !== null) {
        inputValues('attachName', promise);
        inputValues('attachNote', promise);
    }
    attachSave(promise);
}

function createAttach() {
    let file = document.getElementById('createAttach').files[0];
    if (file.size <= 1024 * 1024 * 3) {
        console.log(file);

        let path = file.name;
        let attachName = file.name;
        let date = new Date().toISOString().slice(0, 10);
        let attachNote = '';

        let promise = {file, path, attachName, date, attachNote, contactId};
        console.log(promise);

        let form = new FormData();
        form.append('file', file);
        inputAttachForm.push(form);

        showAttachInputs(promise);
    } else {
        alert("The size of the selected file should not exceed 3 MB");
    }
}

function attachSave(promise) {
    document.getElementById('attachOk').addEventListener("click", function () {
        let elementTable = document.getElementById('attach-table');

        let innerArray = {};
        let inputAttachForm = new FormData(document.forms.attachPopupForm);
        inputAttachForm.append('path', promise.path);
        inputAttachForm.append('date', promise.date);
        if (attachId === 0) {
            inputAttachForm.forEach(function (value, key) {
                innerArray[key] = value;
            });

            createAttachArray.push(innerArray);

            let row = elementTable.insertRow(1);

            let attachName = innerArray['attachName'];
            let note = innerArray['note'];
            let date = promise.date;

            row.insertCell(0).innerHTML = "<input type='checkbox' name='delete'>";
            row.insertCell(1).innerHTML = "<a style='cursor:pointer' id='attach0' onclick='downloadAttachFetch(id)'>" + attachName + "</a>";
            row.insertCell(2).innerHTML = date;
            row.insertCell(3).innerHTML = note;
        } else {
            inputAttachForm.append('id', attachId);
            inputAttachForm.forEach(function (value, key) {
                innerArray[key] = value;
            });
            updateAttachArray.push(innerArray);

            let attachName = innerArray['attachName'];
            let note = innerArray['note'];
            let date = promise.date;

            document.getElementById('attachName' + attachId).innerHTML = attachName;
            document.getElementById('date' + attachId).innerHTML = date;
            document.getElementById('attachNote' + attachId).innerHTML = note;
        }
        manageScripts('attach-window');
    }, false);
}

function attachPopUpWindow(promise) {
    var textHTML = "<fieldset id='attache-fieldset'>" +
        "<form id='attachPopupForm' enctype='multipart/form-data'>" +
        "<label> " +
        "<br>" +
        "File name:" +
        "<br>" +
        "<input name='attachName' id='attachName' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Note:" +
        "<br>" +
        "<input name='note' id='attachNote'>" +
        "</label> " +
        "<br>" +
        "</form>" +
        "<div id='attach-popup-buttons'></div>" +
        "</fieldset>";
    const element = document.getElementById('attach-window');
    element.innerHTML = textHTML;
    element.style.display = 'block';
    addAttachPopupButtons(promise);
}

function addAttachPopupButtons() {
    var textHTML = "<button id='attachOk' type='submit'>Ok</button>" +
        "<button type='reset' onclick='cancelButton(\"attach-window\")'>Cancel</button>";
    document.getElementById('attach-popup-buttons').innerHTML = textHTML;
}

async function fillAttachInputs(id) {
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
        return fetch("application?getAttachById", options)
            .then(response => {
                return resolve(response.json());
            })
            .catch(function (error) {
                reject(new Error(error.message));
            })
    });
    promise['attachNote'] = promise['note'];
    delete promise['note'];
    showAttachInputs(promise);
}