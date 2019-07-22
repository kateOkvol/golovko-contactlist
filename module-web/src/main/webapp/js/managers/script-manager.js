function manageScripts() {


    const edit_element = document.getElementById("contact-editor");
    const main_element = document.getElementById("main-contact");
    const main_value = window.getComputedStyle(main_element, null).getPropertyValue('display');
    const edit_value = window.getComputedStyle(edit_element, null).getPropertyValue('display');
    const phones_pop_element = document.getElementById("phones-window");
    const phones_pop_value = window.getComputedStyle(phones_pop_element, null).getPropertyValue('display');
    const attach_pop_element = document.getElementById("attach-window");
    const attach_pop_value = window.getComputedStyle(attach_pop_element, null).getPropertyValue('display');

    if (attach_pop_value === 'block') {
        attach_pop_element.style.display = 'none';
    }
    else if (phones_pop_value === 'block') {
        phones_pop_element.style.display = 'none';
    } else if (edit_value === 'block') {
        edit_element.style.display = 'none';
        main_element.style.display = 'block';
        document.getElementById('h1').innerHTML = 'Contacts List';
        loadMain();
    } else if (main_value === 'block') {
        main_element.style.display = 'none';
        edit_element.style.display = 'block';
        document.getElementById('h1').innerHTML = 'Contact Editor';
        loadEditor(event, 0);
    }
}