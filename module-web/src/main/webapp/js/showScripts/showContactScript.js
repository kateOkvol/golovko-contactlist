function showContactInputs(promise, formId, holderId) {
    let textHTML = "<form id="+formId+">" +
        "<label> " +
        "<br>" +
        "First name*:" +
        "<br>" +
        "<input name='firstName' id='firstName' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Middle name:" +
        "<br>" +
        "<input name='middleName' id='middleName'>" +
        "</label> " +
        "<br>" +
        "<label> " +
        "Last name*:" +
        "<br>" +
        "<input name='lastName'  id='lastName' required> " +
        "</label> " +
        "<br>" +
        "<label> " +
        "Birthday:" +
        "<br>" +
        "<input name='birthDate' type='date' id='birthDate'> " +
        "</label> " +
        "<label> " +
        "<br>" +
        "Gender:" +
        "<br>" +
        "<select  name = 'gender' id='gender'>" +
        "<option value='' selected>not chosen</option>" +
        "<option value='male'>male</option>" +
        "<option value='female'>female</option>" +
        "</select> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Citizenship:" +
        "<br>" +
        "<input name='citizenship' id='citizenship'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Web Site:" +
        "<br>" +
        "<input name='webSite' id='webSite'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "email:" +
        "<br>" +
        "<input name='email' id='email'> " +
        "</label> " +
        "<label>" +
        "<br>" +
        "Marital status:" +
        "<br>" +
        "<select  name='maritalStatus' id='maritalStatus'>" +
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
        "<input name='company' id='company'> " +
        "</label>" +
        "</form>";
    document.getElementById(holderId).innerHTML = textHTML;

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