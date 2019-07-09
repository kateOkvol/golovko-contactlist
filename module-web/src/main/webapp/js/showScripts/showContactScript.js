function showContactInputs(promise) {
    var textHTML = "<label> " +
        "<br>" +
        "First name*:" +
        "<br>" +
        "<input name='input-label' id='firstName' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Middle name:" +
        "<br>" +
        "<input name='input-label' id='middleName'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Last name*:" +
        "<br>" +
        "<input name='input-label'  id='lastName' required> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "<label> " +
        "Birthday:" +
        "<br>" +
        "<input name='input-label' type='date' id='birthDate'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Gender:" +
        "<br>" +
        "<select  id='gender'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='male'>male</option>" +
        "<option value='female'>female</option>" +
        "</select> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Citizenship:" +
        "<br>" +
        "<input name='input-label' id='citizenship'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Web Site:" +
        "<br>" +
        "<input name='input-label' id='webSite'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "email:" +
        "<br>" +
        "<input name='input-label' id='email'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Marital status:" +
        "<br>" +
        "<select  id='maritalStatus'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='single'>single</option>" +
        "<option value='in a relationship'>in a relationship</option>" +
        "<option value='married'>married</option>" +
        "</select> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Company:" +
        "<br>" +
        "<input name='input-label' id='company'> " +
        "</label>";
    document.getElementById('content').innerHTML = textHTML;

    if (promise == null) {
        noInputValues('input-label');
    } else {
        inputValues('firstName', promise);
        inputValues('middleName', promise);
        inputValues('lastName', promise);
        inputValues('birthDate', promise);
        inputValues('gender', promise);
        inputValues('citizenship', promise);
        inputValues('webSite', promise);
        inputValues('email', promise);
        inputValues('maritalStatus', promise);
        inputValues('company', promise);
    }
}