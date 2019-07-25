function manageScripts(element, pageId) {
//pageId - id of active div
    switch (element) {
        case("phones-window"):
            document.getElementById(element).style.display = 'none';
            break;
        case("attach-window"):
            document.getElementById(element).style.display = 'none';
            break;
        case("contact-editor"):
        case("email-page"):
            document.getElementById(element).style.display = 'none';
            document.getElementById("main-contact").style.display = 'block';
            document.getElementById('h1').innerHTML = 'Contacts List';
            loadMain();
            break;
        case("main-contact"):
            document.getElementById(element).style.display = 'none';
            document.getElementById(pageId).style.display = 'block';
            if (pageId === 'contact-editor') {
                loadEditor(event, 0);
            }
            if (pageId === 'email-page') {
                loadEmail(event);
            }
            break;
    }
}