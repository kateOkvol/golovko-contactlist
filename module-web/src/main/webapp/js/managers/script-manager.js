function manageScripts() {
    const edit_element = document.getElementById("contact-editor");
    const main_element = document.getElementById("main-contact");
    const main_value = window.getComputedStyle(main_element, null).getPropertyValue('display');
    const edit_value = window.getComputedStyle(edit_element, null).getPropertyValue('display');
    const phones_pop_element = document.getElementById("phones-window");
    const phones_pop_value = window.getComputedStyle(phones_pop_element, null).getPropertyValue('display');

    if (phones_pop_value === 'block') {
        phones_pop_element.style.display = 'none';
    } else if (edit_value === 'block') {
        edit_element.style.display = 'none';
        main_element.style.display = 'block';
        loadMain();
        console.log('main function is run');
    } else if (main_value === 'block') {
        main_element.style.display = 'none';
        edit_element.style.display = 'block';
        loadEditor(event, 0);
        console.log('edit function is run');
    }
}