function showAddressInputs(promise) {
    const textHTML = "<form id='addressInputs'>" +
        "<label>" +
        "<br>" +
        "Country:" +
        "<br>" +
        "<input name='country' id='country'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "City:" +
        "<br>" +
        "<input name='city' id='city'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Street:" +
        "<br>" +
        "<input name='street' id='street'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "House:" +
        "<br>" +
        "<input name='house' id='house'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Flat:" +
        "<br>" +
        "<input name='flat' id='flat'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Zip code:" +
        "<br>" +
        "<input name='zipCode' id='zipCode'> " +
        "</label>" +
        "</form>";

    document.getElementById('addressContent').innerHTML = textHTML;

    if (promise !== null) {
        inputValues('country', promise);
        inputValues('city', promise);
        inputValues('street', promise);
        inputValues('house', promise);
        inputValues('flat', promise);
        inputValues('zipCode', promise);
    }
}