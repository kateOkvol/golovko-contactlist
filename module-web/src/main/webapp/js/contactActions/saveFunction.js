function contactSave(contactId) {
    const id = contactId;
    const firstName = document.getElementById('firstName').value;
    const middleName = document.getElementById('middleName').value;
    const lastName = document.getElementById('lastName').value;
    const birthDate = document.getElementById('birthDate').value;
    const citizenship = document.getElementById('citizenship').value;
    const webSite = document.getElementById('webSite').value;
    const email = document.getElementById('email').value;
    const company = document.getElementById('company').value;

    const genderElement = document.getElementById('gender');
    const gender = genderElement.options[genderElement.selectedIndex].value;
    const maritalStatusElement = document.getElementById('maritalStatus');
    const maritalStatus = maritalStatusElement.options[maritalStatusElement.selectedIndex].value;

    const country = document.getElementById('country').value;
    const city = document.getElementById('city').value;
    const street = document.getElementById('street').value;
    const house = document.getElementById('house').value;
    const flat = document.getElementById('flat').value;
    const zipCode = document.getElementById('zipCode').value;

    var contactData;
    var url;
    if (contactId === 0) {
        contactData = {
            firstName, middleName, lastName, birthDate, gender, citizenship, webSite, email, maritalStatus, company,
            country, city, street, house, flat, zipCode
        };
        url = 'application?command=createContact';
    } else {
        contactData = {
            id, firstName, middleName, lastName, birthDate, gender, citizenship, webSite, email, maritalStatus, company,
            country, city, street, house, flat, zipCode
        };
        url = 'application?command=updateContact';
    }

    buttonSave(url, contactData);
}

async function buttonSave(url, data) {
    let returnData = await new Promise((resolve, reject) => {
            return fetch(url, {
                method: 'POST',
                headers: {
                    'Accept-type': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .catch(function (error) {
                    reject(new Error(error.message));
                })
        }
    );
    console.log(returnData);
    manageScripts();
    noInputValues('input-label');
}