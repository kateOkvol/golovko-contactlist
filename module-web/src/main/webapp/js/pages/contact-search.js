function loadSearch(event) {
    event.preventDefault();
    document.getElementById('h1').innerHTML = 'Contact Search';
    showSearchInputs();
    addSearchButtons();
}

function addSearchButtons() {
    let textHTML = "<button id='search-but'>Search</button>" +
        "<button onclick='cancelButton(\"search-page\")'>Cancel</button>";
    document.getElementById('search-buttons').innerHTML = textHTML;
    let inp = document.getElementById('firstNameS');
    document.getElementById('search-but').addEventListener('click', processSearch, false);
}

function showSearchInputs() {
    const textHTMLC = "<form id='search-contact-inputs'>" +
        "<label> " +
        "<br>" +
        "First name:" +
        "<br>" +
        "<input name='firstName' id='firstNameS' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Middle name:" +
        "<br>" +
        "<input name='middleName' id='middleNameS'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Last name:" +
        "<br>" +
        "<input name='lastName'  id='lastNameS' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Birthday:" +
        "<br>" +
        "<select  name = 'filter' id='filterS'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='more'>more</option>" +
        "<option value='less'>less</option>" +
        "<input name='birthDate' type='date' id='birthDateS'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Gender:" +
        "<br>" +
        "<select  name = 'gender' id='genderS'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='male'>male</option>" +
        "<option value='female'>female</option>" +
        "</select> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Citizenship:" +
        "<br>" +
        "<input name='citizenship' id='citizenshipS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Marital status:" +
        "<br>" +
        "<select  name='maritalStatus' id='maritalStatusS'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='single'>single</option>" +
        "<option value='in a relationship'>in a relationship</option>" +
        "<option value='married'>married</option>" +
        "</select> " +
        "</label> " +
        "</form>";
    document.getElementById('contact-search').innerHTML = textHTMLC;

    inputValues('firstNameS', null);
    inputValues('middleNameS', null);
    inputValues('lastNameS', null);
    inputValues('birthDateS', null);
    inputValues('genderS', null);
    inputValues('citizenshipS', null);
    inputValues('maritalStatusS', null);

    const textHTMLA = "<form id='search-address-inputs'>" +
        "<label>" +
        "<br>" +
        "Country:" +
        "<br>" +
        "<input name='country' id='countryS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "City:" +
        "<br>" +
        "<input name='city' id='cityS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Street:" +
        "<br>" +
        "<input name='street' id='streetS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "House:" +
        "<br>" +
        "<input name='house' id='houseS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Flat:" +
        "<br>" +
        "<input name='flat' id='flatS'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Zip code:" +
        "<br>" +
        "<input name='zipCode' id='zipCodeS'> " +
        "</label>" +
        "</form>";

    document.getElementById("address-search").innerHTML = textHTMLA;

    inputValues('countryS', null);
    inputValues('cityS', null);
    inputValues('streetS', null);
    inputValues('houseS', null);
    inputValues('flatS', null);
    inputValues('zipCodeS', null);
}

function processSearch() {
    let personalData = new FormData(document.forms['search-contact-inputs']);
    let addressData = new FormData(document.forms['search-address-inputs']);
    let form = createPostContactData(personalData, addressData);
    return fetch("application?searchContacts", {
            method: 'POST',
            headers: {
                'Accept-type': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(form)
        }
    )
        .then(response => {
            return response.json().catch(error => {
                return Promise.reject(new Error('Invalid JSON: ' + error.message));
            });
        })
        .then(response => {
            contactsList = response;

            document.getElementById("search-page").style.display = 'none';
            document.getElementById("main-contact").style.display = 'block';
            document.getElementById('h1').innerHTML = 'Contacts List';

            tableText();
            let template = document.getElementById("template-table").innerHTML;
            console.log(template);
            document.getElementById("contact-table").innerHTML = Mustache.to_html(template, contactsList);
        })
        .catch(function (error) {
            console.log(error);
        });

}