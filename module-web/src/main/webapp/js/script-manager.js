function manageScripts() {
    const main_element = document.getElementById("main-contact");

    const edit_element = document.getElementById("contact-editor");


    const main_value = window.getComputedStyle(main_element, null).getPropertyValue('display');
    if(main_value === 'block'){
        edit_element.style.display = 'none';
        loadMain();
        console.log('main function is run');
    } else {
        console.log('main is none');
    }

    const edit_value = window.getComputedStyle(edit_element, null).getPropertyValue('display');
        if(edit_value === 'block'){
        main_element.style.display = 'none';
        loadEditor(0);
        console.log('edit function is run');
    } else {
        console.log('edit is none');
    }


}