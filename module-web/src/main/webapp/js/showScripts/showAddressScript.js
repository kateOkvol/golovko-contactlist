function showAddressInputs(promise) {
    const textHTML = "<label>" +
        "<br>" +
        "Country:" +
        "<br>" +
        "<input name='input-label' id='country'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "City:" +
        "<br>" +
        "<input name='input-label' id='city'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Street:" +
        "<br>" +
        "<input name='input-label' id='street'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "House:" +
        "<br>" +
        "<input name='input-label' id='house'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Flat:" +
        "<br>" +
        "<input name='input-label' id='flat'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Zip code:" +
        "<br>" +
        "<input name='input-label' id='zipCode'> " +
        "</label> ";

    document.getElementById('addressContent').innerHTML = textHTML;

    if (promise == null) {
        noInputValues();
    } else {
        inputValues('country', promise);
        inputValues('city', promise);
        inputValues('street', promise);
        inputValues('house', promise);
        inputValues('flat', promise);
        inputValues('zipCode', promise);
    }
}