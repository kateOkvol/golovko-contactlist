function loadEmail(event) {
    event.preventDefault();
    document.getElementById('h1').innerHTML = 'Email Sender';
    let recipients = printRecipientsNames();
    addEmailButtons();
    let promise = getTemplatesFetch();
    promise.then(data=>{
       previewMessage(data);
    });
    sendEmail(recipients);
}

function printRecipientsNames() {
    let selectedId = [];
    let names = '';
    let tagNames = document.getElementById('names');
    const tableBody = document.getElementById("table-body");
    const elements = tableBody.getElementsByTagName('input');
    for (var i = elements.length - 1; i >= 0; i--) {
        if (elements[i].checked) {
            const newId = elements[i].id.replace(/\D+/g, "");
            names += " " + tableBody.rows[i].cells[1].lastChild.innerText + ",";
            selectedId.push(newId);
        }
    }
    names = names.substring(0, names.length - 2);
    tagNames.innerHTML = names;
    return selectedId;
}

function getTemplatesFetch() {
    return fetch("application?getTemplates", {
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            return response.json();
        })
        .catch(error => {
            console.log(new Error('Invalid JSON: ' + error.message));
        });
}

function previewMessage(promise) {
    let select = document.getElementById('templ-select');
    select.addEventListener("change", function () {
        let templ = select[select.selectedIndex].value;
        document.getElementById("message-area").value = promise[templ];
    }, false);
}

function addEmailButtons() {
    let textHTML = "<button type='submit' id= 'send-email'>Send</button> " +
        "<button type='reset' id= 'cancel-creator' onclick='cancelButton(\"email-page\")'>Cancel</button>";
    document.getElementById('email-buttons').innerHTML = textHTML;
}

function sendEmail(recipients) {
    document.getElementById('send-email').addEventListener('click', function () {
        let message = document.getElementById("message-area").value;
        let topic = document.getElementById("topic-input").value;
        let email = {};
        email.message = message;
        email.topic = topic;
        email.recipients = recipients;

        let emailPromise = new Promise((resolve, reject) => {
            return fetch('application?sendEmail', {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(email)
            })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        });
        console.log(emailPromise);
        manageScripts("email-page");
    }, false);
}